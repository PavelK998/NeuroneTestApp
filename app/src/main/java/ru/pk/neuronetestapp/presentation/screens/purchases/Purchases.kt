package ru.pk.neuronetestapp.presentation.screens.purchases

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.presentation.components.BackButton
import ru.pk.neuronetestapp.presentation.components.Loading
import ru.pk.neuronetestapp.presentation.screens.purchases.components.PurchaseCard
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.background

@Composable
fun PurchasesScreenRoot(
    viewModel: PurchasesViewModel,
    onBackClick: () -> Unit
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    PurchasesScreen(
        uiState = uiState,
        handleIntent = { intent ->
            when(intent) {
                is PurchasesIntent.OnNavigationIconClick -> onBackClick()
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PurchasesScreen(
    uiState: PurchasesState,
    handleIntent: (PurchasesIntent) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = background,
        topBar = {
            TopAppBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets.displayCutout),
                colors = TopAppBarDefaults.topAppBarColors().copy(
                    containerColor = background,
                ),
                navigationIcon = {
                    BackButton(
                        modifier = Modifier.padding(start = 16.dp),
                        onClick = {
                            handleIntent(PurchasesIntent.OnNavigationIconClick)
                        }
                    )
                },
                title = {
                    Text(
                        modifier = Modifier.padding(start = 16.dp),
                        text = stringResource(
                        R.string.your_purchases),
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            )
        },
        contentWindowInsets = WindowInsets.safeDrawing
    ) { paddingValues ->
        Crossfade(targetState = uiState.isLoading, label = "cross fade") { isLoading ->
            when (isLoading) {
                true -> {
                    Loading()
                }
                false -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)

                    ) {
                        items(uiState.listPurchaseUiModels) { purchaseUiModel ->
                            PurchaseCard(model = purchaseUiModel)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    NeuroneTestAppTheme {
        PurchasesScreen(
            uiState = PurchasesState(),
            handleIntent = {}
        )
    }
}