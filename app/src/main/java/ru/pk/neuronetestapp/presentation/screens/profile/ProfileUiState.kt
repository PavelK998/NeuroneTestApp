package ru.pk.neuronetestapp.presentation.screens.profile

data class ProfileUiState(
    val firstName: String = "",
    val lastName: String = "",
    val phoneNumber: String = "+7 921 555 55 55",
    val language: String = "",
    val email: String = "testUser@Gmail.com",
    val emailError: String = ""
)