package ru.pk.neuronetestapp.presentation.screens.profile

sealed interface ProfileIntent {
    data object OnRegisterBankClientClick : ProfileIntent

    data object OnPurchasesClick : ProfileIntent

    data class OnBiometryEnableBtnClick(val isEnabled: Boolean) : ProfileIntent
}