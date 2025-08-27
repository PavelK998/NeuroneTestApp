package ru.pk.neuronetestapp.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.domain.ResourceManager

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState = _uiState
        .onStart {
            _uiState.update {
                it.copy(
                    language = resourceManager.getString(R.string.mock_language),
                    emailError = resourceManager.getString(R.string.mock_email_error)
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ProfileUiState()
        )
}