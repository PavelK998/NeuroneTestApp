package ru.pk.neuronetestapp.presentation.screens.registration

sealed interface RegistrationUiEvent {
    data object NavigateUp: RegistrationUiEvent
}