package ru.pk.neuronetestapp.presentation.screens.purchases

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.pk.neuronetestapp.domain.repository.NetworkRepository
import ru.pk.neuronetestapp.presentation.mappers.toListPurchaseUiModel
import ru.pk.neuronetestapp.utils.execute

@HiltViewModel
class PurchasesViewModel @Inject constructor(
    private val networkRepository: NetworkRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PurchasesState())
    val uiState = _uiState
        .onStart {
            getDataFronServer()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = PurchasesState()
        )

    private fun getDataFronServer() = viewModelScope.execute(
        source = {
            networkRepository.getDataFromServer()
        },
        onSuccess = { listNetworkModel ->
            _uiState.update { it.copy(listPurchaseUiModels = listNetworkModel.toListPurchaseUiModel()) }
        },
        onLoading = { isLoading ->
            _uiState.update {
                it.copy(
                    isLoading = isLoading
                )
            }
        },
        onError = {

        }
    )
}