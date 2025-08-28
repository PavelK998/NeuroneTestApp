package ru.pk.neuronetestapp.presentation.screens.purchases.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.pk.neuronetestapp.presentation.screens.purchases.ui_model.PurchaseUiModel
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.clickableImageOrTextButton
import ru.pk.neuronetestapp.ui.theme.innerCardInPurchaseCard
import java.time.LocalDateTime

@Composable
fun PurchaseCard(
    modifier: Modifier = Modifier,
    model: PurchaseUiModel
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors().copy(
            clickableImageOrTextButton
        )
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = model.uiDate,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onBackground
            )
            PurchaseInnerCard(
                listNames = model.purchasesListNames
            )
        }

    }
}

@Composable
fun PurchaseInnerCard(listNames: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            //Нельзя создать вложенный прокручиваемый контейнер с бесконечный высотой
            //ограничиваем высоту в 400 dp
            .heightIn(max = 400.dp)
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(listNames) { name ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                shape = RoundedCornerShape(4.dp),
                colors = CardDefaults.cardColors().copy(
                    containerColor = innerCardInPurchaseCard
                )
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    text = name,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
private fun PurchaseCardPreview() {
    NeuroneTestAppTheme {
        PurchaseCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            model = PurchaseUiModel(
                date = LocalDateTime.now(),
                uiDate = "10.05.2025",
                purchasesListNames = (1..5).map {
                    it.toString()
                }
            )
        )
    }
}