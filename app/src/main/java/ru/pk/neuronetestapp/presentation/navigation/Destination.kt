package ru.pk.neuronetestapp.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Destination {
    @Serializable
    data object ProfileScreen : Destination

    @Serializable
    data object RegisterScreen : Destination

    @Serializable
    data object PurchasesScreen : Destination
}