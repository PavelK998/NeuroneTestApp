package ru.pk.neuronetestapp.presentation.screens.profile

sealed interface ProfileIntent {
    data object OnRegisterBankClientClick : ProfileIntent

    data object OnPurchasesClick : ProfileIntent
}