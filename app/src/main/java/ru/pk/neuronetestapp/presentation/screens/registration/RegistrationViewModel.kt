package ru.pk.neuronetestapp.presentation.screens.registration

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.domain.manager.DeviceManager
import ru.pk.neuronetestapp.domain.manager.ResourceManager
import ru.pk.neuronetestapp.domain.model.UserDataModel
import ru.pk.neuronetestapp.utils.execute

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val deviceManager: DeviceManager,
    private val resourceManager: ResourceManager
) : ViewModel() {

    private val _uiEvent = Channel<RegistrationUiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val _uiState = MutableStateFlow(RegistrationState())
    val uiState = _uiState
        .onStart {
            validateFieldsForActivateButton()
            _uiState.update {
                it.copy(
                    termsOfUseText = createTermsOfUseText()
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = RegistrationState()
        )

    fun handleIntent(intent: RegistrationIntent) {
        when (intent) {
            is RegistrationIntent.OnBackButtonClick -> {

            }

            is RegistrationIntent.OnTermsOfUseCLick -> {

            }

            is RegistrationIntent.OnSixteenDigitsTextChange -> {
                if (_uiState.value.isSixteenDigitsCodeError) {
                    _uiState.update {
                        it.copy(
                            isSixteenDigitsCodeError = false
                        )
                    }
                }
                _uiState.update {
                    it.copy(
                        sixteenDigitsCode = intent.text
                    )
                }
            }

            is RegistrationIntent.OnCodeTextChange -> {
                if (_uiState.value.isCodeError) {
                    _uiState.update {
                        it.copy(
                            isCodeError = false
                        )
                    }
                }
                _uiState.update {
                    it.copy(
                        code = intent.text
                    )
                }
            }

            is RegistrationIntent.OnFirstNameTextChange -> {
                _uiState.update {
                    it.copy(
                        firstName = intent.text
                    )
                }
            }

            is RegistrationIntent.OnLastNameTextChange -> {
                _uiState.update {
                    it.copy(
                        lastName = intent.text
                    )
                }
            }

            is RegistrationIntent.OnRegisterButtonClick -> {
                registration()
            }

            is RegistrationIntent.CodeFieldLoseFocus -> {
                if (_uiState.value.code.isNotBlank() &&_uiState.value.code.length < 4) {
                    _uiState.update {
                        it.copy(
                            isCodeError = true
                        )
                    }
                }
            }

            is RegistrationIntent.SixteenDigitsFieldLoseFocus -> {
                if (_uiState.value.sixteenDigitsCode.isNotBlank() && _uiState.value.sixteenDigitsCode.length < 16) {
                    _uiState.update {
                        it.copy(
                            isSixteenDigitsCodeError = true
                        )
                    }
                }
            }
        }
    }

    //Создаем строку с подчеркнутой частью
    private fun createTermsOfUseText(): AnnotatedString{
        val fullText = resourceManager.getString(R.string.confirm_terms)
        val underlinedPartOfText = resourceManager.getString(R.string.confirm_terms_clickable_part)
        return buildAnnotatedString {
            append(fullText)
            val startIndex = fullText.indexOf(underlinedPartOfText)
            val endIndex = startIndex + underlinedPartOfText.length
            addStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                ),
                start = startIndex,
                end = endIndex
            )
        }
    }

    //Подписываемся на изменения состояния и проверяем корректность заполнения полей
    // если все поля корректно заполнены, активируем кнопку регистрации
    @OptIn(FlowPreview::class)
    private fun validateFieldsForActivateButton() = viewModelScope.launch {
        _uiState
            .debounce(300L)
            .collect { state ->
                val isDataValid =
                    _uiState.value.firstName.isNotBlank() && _uiState.value.lastName.isNotBlank()
                if (isDataValid != _uiState.value.isButtonRegisterEnabled) {
                    _uiState.update { it.copy(isButtonRegisterEnabled = isDataValid) }
                }
            }
    }

    private fun registration() {
        //Дополнительно проверяем поля, т.к. кнопка деактивируется с задержкой
        // и на нее теоретически можно успеть нажать
        val isDataValid =
            _uiState.value.firstName.isNotBlank()
                    && _uiState.value.lastName.isNotBlank()
                    && _uiState.value.code.length >= 4
                    && _uiState.value.sixteenDigitsCode.length == 16
        if (isDataValid && _uiState.value.isButtonRegisterEnabled) {
            viewModelScope.execute(
                source = {
                    deviceManager.setUserData(
                        UserDataModel(
                            sixteenDigitsCode = _uiState.value.sixteenDigitsCode,
                            code = _uiState.value.code,
                            firstName = _uiState.value.firstName,
                            lastName = _uiState.value.lastName
                        )
                    )
                },
                //При успехе отправляем one time event для навигации на предыдущий экран
                onSuccess = {
                    _uiEvent.trySend(RegistrationUiEvent.NavigateUp)
                }
            )
        }
    }
}