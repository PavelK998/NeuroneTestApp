package ru.pk.neuronetestapp.presentation.screens.purchases

import ru.pk.neuronetestapp.presentation.screens.purchases.ui_model.PurchaseUiModel

data class PurchasesState(
    val listPurchaseUiModels: List<PurchaseUiModel> = emptyList(),
    val isLoading: Boolean = false
)