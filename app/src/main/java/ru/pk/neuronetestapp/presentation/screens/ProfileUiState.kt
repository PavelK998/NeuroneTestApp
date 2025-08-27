package ru.pk.neuronetestapp.presentation.screens

data class ProfileUiState(
    val firstName: String = "art",
    val lastName: String = "art",
    val phoneNumber: String = "+7 921 555 55 55",
    val language: String = "",
    val email: String = "testUser@Gmail.com",
    val emailError: String = "Необходимо подтвердить"
)