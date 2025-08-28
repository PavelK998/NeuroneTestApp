package ru.pk.neuronetestapp.presentation.screens.registration

import androidx.compose.runtime.Immutable
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.AnnotatedString

@Immutable
data class RegistrationState(
    val sixteenDigitsCode: String = "",
    val code: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val termsOfUseText: AnnotatedString = AnnotatedString(text = ""),
    val listOfFocusRequesters: List<FocusRequester> = List(4, {FocusRequester()}),
    val isButtonRegisterEnabled: Boolean = false,
    val isSixteenDigitsCodeError: Boolean = false,
    val isCodeError: Boolean = false,
)