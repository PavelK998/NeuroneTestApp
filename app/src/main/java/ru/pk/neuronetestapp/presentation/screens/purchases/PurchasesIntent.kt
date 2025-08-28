package ru.pk.neuronetestapp.presentation.screens.purchases

sealed interface PurchasesIntent {
    data object OnNavigationIconClick : PurchasesIntent
}