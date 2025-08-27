package ru.pk.neuronetestapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.additionallyText
import ru.pk.neuronetestapp.ui.theme.clickableImageOrTextButton
import ru.pk.neuronetestapp.ui.theme.clickableImageOrTextButtonArrow

@Composable
fun ClickableImageOrTextButton(
    modifier: Modifier = Modifier,
    drawableRes: Int? = null,
    text: String,
    trailingText: String = "",
    errorText: String = "",
    omClick: () -> Unit

) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(
            containerColor = clickableImageOrTextButton
        ),
        onClick = omClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (drawableRes != null) 10.dp else 20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (drawableRes != null) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp)
                        .size(40.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        modifier = Modifier
                            .size(40.dp),
                        painter = painterResource(drawableRes),
                        contentDescription = null
                    )
                }
            } else {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 12.dp),
                    color = additionallyText,
                    text = text
                )
            }
            Column(
                modifier = Modifier.width(IntrinsicSize.Max)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    if (trailingText.isNotBlank()) {
                        Text(
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End,
                            color = Color.White,
                            text = trailingText,
                            maxLines = 1
                        )
                    }
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                        contentDescription = null,
                        tint = clickableImageOrTextButtonArrow
                    )
                }
                if (errorText.isNotBlank()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            style = MaterialTheme.typography.bodySmall,
                            text = errorText,
                            color = MaterialTheme.colorScheme.error,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun ClickableImageOrTextButtonPreview() {
    NeuroneTestAppTheme {
        ClickableImageOrTextButton(
            text = "Язык",
            trailingText = "Русский",
            errorText = "Необходимо подтвердить",
            omClick = {}
        )
    }
}