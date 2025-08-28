package ru.pk.neuronetestapp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.button

@Composable
fun BackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .width(IntrinsicSize.Min),
        border = BorderStroke(
            width = 2.dp,
            color = Color.Black
        ),
        colors = CardDefaults.cardColors().copy(
            containerColor = button
        ),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 11.dp
        ),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp, horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = Color.White
            )
            Text(
                text = stringResource(R.string.back_btn_text),
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BackButtonPreview() {
    NeuroneTestAppTheme {
        BackButton(
            onClick = {}
        )
    }
}