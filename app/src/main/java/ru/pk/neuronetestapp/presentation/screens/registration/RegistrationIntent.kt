package ru.pk.neuronetestapp.presentation.screens.registration

sealed interface RegistrationIntent {
    data object OnBackButtonClick : RegistrationIntent

    data object OnRegisterButtonClick : RegistrationIntent

    data object OnTermsOfUseCLick : RegistrationIntent

    data object SixteenDigitsFieldLoseFocus : RegistrationIntent

    data object CodeFieldLoseFocus : RegistrationIntent

    data class OnSixteenDigitsTextChange(val text: String) : RegistrationIntent

    data class OnCodeTextChange(val text: String) : RegistrationIntent

    data class OnFirstNameTextChange(val text: String) : RegistrationIntent

    data class OnLastNameTextChange(val text: String) : RegistrationIntent
}