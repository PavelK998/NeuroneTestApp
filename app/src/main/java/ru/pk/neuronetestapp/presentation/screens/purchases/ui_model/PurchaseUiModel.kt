package ru.pk.neuronetestapp.presentation.screens.purchases.ui_model

import java.time.LocalDateTime

data class PurchaseUiModel(
    val date: LocalDateTime?,
    val uiDate: String,
    val purchasesListNames: List<String>
)
