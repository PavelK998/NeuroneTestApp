package ru.pk.neuronetestapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.additionallyText
import ru.pk.neuronetestapp.ui.theme.clickableImageOrTextButton

@Composable
fun ClickableTextButtonWithSwitch(
    modifier: Modifier = Modifier,
    text: String,
    isSwitchChecked: Boolean,
    onSwitchClick: (Boolean) -> Unit,
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
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp),
                color = additionallyText,
                text = text
            )
            Switch(
                checked = isSwitchChecked,
                onCheckedChange = {
                    onSwitchClick(it)
                },
                colors = SwitchDefaults.colors()
                    .copy(
                        checkedTrackColor = MaterialTheme.colorScheme.error,
                        uncheckedThumbColor = Color.White,
                        uncheckedTrackColor = additionallyText
                    )
            )
        }
    }
}

@Preview
@Composable
private fun ClickableImageOrTextButtonPreview() {
    NeuroneTestAppTheme {
        ClickableTextButtonWithSwitch(
            text = "Язык",
            omClick = {},
            isSwitchChecked = true,
            onSwitchClick = {}
        )
    }
}