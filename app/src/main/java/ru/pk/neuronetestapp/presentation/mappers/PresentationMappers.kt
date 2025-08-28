package ru.pk.neuronetestapp.presentation.mappers

import ru.pk.neuronetestapp.domain.model.NetworkDataModel
import ru.pk.neuronetestapp.presentation.screens.purchases.ui_model.PurchaseUiModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

fun NetworkDataModel.toPurchaseUiModel(): PurchaseUiModel {
    val date = try {
        val zonedDateTime = ZonedDateTime.parse(date)
        zonedDateTime.toLocalDateTime()
    } catch (e: DateTimeParseException) {
        e.printStackTrace()
        null
    }
    val uiDate = if (this.date.isBlank()) {
        ""
    } else {
        if (date != null) {
            DateTimeFormatter.ofPattern("dd.MM.yyyy").format(date)
        } else {
            ""
        }
    }
    return PurchaseUiModel(
        date = date,
        uiDate = uiDate,
        purchasesListNames = purchasesListNames
    )
}

fun List<NetworkDataModel>.toListPurchaseUiModel(): List<PurchaseUiModel> = map {
    it.toPurchaseUiModel()
}