package ru.pk.neuronetestapp.presentation.screens

sealed interface ProfileIntent {
    data object OnRegisterBankClientClick : ProfileIntent

    data object OnPurchasesClick : ProfileIntent
}