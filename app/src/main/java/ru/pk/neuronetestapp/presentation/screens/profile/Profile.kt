package ru.pk.neuronetestapp.presentation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.presentation.components.BackButton
import ru.pk.neuronetestapp.presentation.components.ClickableImageOrTextButton
import ru.pk.neuronetestapp.presentation.components.ClickableTextButtonWithSwitch
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.additionallyText
import ru.pk.neuronetestapp.ui.theme.background


@Composable
fun ProfileScreenRoot(
    viewModel: ProfileViewModel,
    onPurchasesClick: () -> Unit,
    onRegisterBankClientClick: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    ProfileScreen(
        uiState = uiState,
        handleIntent = { intent ->
            when (intent) {
                is ProfileIntent.OnPurchasesClick -> {
                    onPurchasesClick()
                }

                is ProfileIntent.OnRegisterBankClientClick -> {
                    onRegisterBankClientClick()
                }

                else -> {
                    viewModel.handleIntent(intent)
                }
            }
        }
    )
}

@Composable
fun ProfileScreen(
    uiState: ProfileUiState,
    handleIntent: (ProfileIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .verticalScroll(rememberScrollState())
    ) {
        BackButton(
            onClick = {

            }
        )

        Text(
            modifier = Modifier.padding(top = 32.dp),
            style = MaterialTheme.typography.displaySmall,
            color = MaterialTheme.colorScheme.onBackground,
            text = uiState.firstName
        )
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .width(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(top = 8.dp),
                style = MaterialTheme.typography.displaySmall,
                color = MaterialTheme.colorScheme.onBackground,
                text = uiState.lastName
            )
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(32.dp),
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                tint = additionallyText
            )
        }

        Text(
            modifier = Modifier.padding(top = 24.dp),
            style = MaterialTheme.typography.bodyLarge,
            color = additionallyText,
            text = uiState.phoneNumber
        )

        Text(
            modifier = Modifier.padding(top = 24.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = stringResource(R.string.purchases),
            color = additionallyText
        )

        ClickableImageOrTextButton(
            modifier = Modifier.padding(top = 16.dp),
            drawableRes = R.drawable.icon_on,
            text = "",
            omClick = {
                handleIntent(ProfileIntent.OnPurchasesClick)
            }
        )

        Text(
            modifier = Modifier.padding(top = 24.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = stringResource(R.string.settings),
            color = additionallyText
        )

        ClickableImageOrTextButton(
            modifier = Modifier.padding(top = 16.dp),
            text = stringResource(R.string.email),
            trailingText = uiState.email,
            errorText = uiState.emailError,
            omClick = {}
        )
        ClickableTextButtonWithSwitch(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.biometry_entry),
            omClick = {},
            isSwitchChecked = uiState.isBiometryEnabled,
            onSwitchClick = {
                handleIntent(ProfileIntent.OnBiometryEnableBtnClick(it))
            }
        )

        ClickableImageOrTextButton(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.change_code),
            omClick = {}
        )

        ClickableImageOrTextButton(
            modifier = Modifier.padding(top = 8.dp),
            text = stringResource(R.string.registration_for_bank_clients),
            omClick = {
                handleIntent(ProfileIntent.OnRegisterBankClientClick)
            }

        )

        ClickableImageOrTextButton(
            modifier = Modifier.padding(top = 8.dp, bottom = 24.dp),
            text = stringResource(R.string.language),
            trailingText = uiState.language,
            omClick = {}
        )
    }
}

@Preview
@Composable
private fun Preview() {
    NeuroneTestAppTheme {
        ProfileScreen(
            uiState = ProfileUiState(),
            handleIntent = {}
        )
    }
}