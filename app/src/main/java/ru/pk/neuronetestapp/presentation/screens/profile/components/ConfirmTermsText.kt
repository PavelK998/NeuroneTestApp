package ru.pk.neuronetestapp.presentation.screens.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import ru.pk.neuronetestapp.ui.theme.additionallyText

@Composable
fun ConfirmTermsText(
    modifier: Modifier = Modifier,
    annotatedString: AnnotatedString,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    textAlign: TextAlign = TextAlign.Center,
    textColor: Color = additionallyText,
    onClick: () -> Unit
) {
    Text(
        modifier = modifier
            .clickable {
                onClick()
            },
        text = annotatedString,
        textAlign = textAlign,
        color = textColor,
        style = style,
    )
}