package ru.pk.neuronetestapp.presentation.screens.registration

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.presentation.components.BackButton
import ru.pk.neuronetestapp.presentation.screens.profile.components.ConfirmTermsText
import ru.pk.neuronetestapp.presentation.screens.profile.components.DefaultTextField
import ru.pk.neuronetestapp.presentation.screens.profile.components.utils.GroupedFourNumberVisualTransformation
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.additionallyText
import ru.pk.neuronetestapp.ui.theme.background
import ru.pk.neuronetestapp.ui.theme.registrationButton
import ru.pk.neuronetestapp.ui.theme.registrationNotActiveButton
import ru.pk.neuronetestapp.utils.ObserveAsActions


@Composable
fun RegistrationScreenRoot(
    viewModel: RegistrationViewModel,
    onBackButtonClick: () -> Unit
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    ObserveAsActions(viewModel.uiEvent) { event ->
        when (event) {
            is RegistrationUiEvent.NavigateUp -> onBackButtonClick()
        }
    }
    RegistrationScreen(
        uiState = uiState,
        handleIntent = { intent ->
            when (intent) {
                is RegistrationIntent.OnBackButtonClick -> onBackButtonClick()
                is RegistrationIntent.OnTermsOfUseCLick -> {
                    Toast.makeText(
                        context,
                        context.getString(R.string.mock_terms_of_use_click_result),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> viewModel.handleIntent(intent)
            }

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    uiState: RegistrationState,
    handleIntent: (RegistrationIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(horizontal = 16.dp)
    ) {
        BackButton(
            onClick = {
                handleIntent(RegistrationIntent.OnBackButtonClick)
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                modifier = Modifier.padding(top = 24.dp),
                style = MaterialTheme.typography.displaySmall,
                text = stringResource(R.string.registration_for_bank_clients),
                color = MaterialTheme.colorScheme.onBackground
            )

            TextInputBlock(
                modifier = Modifier.imePadding(),
                uiState = uiState,
                handleIntent = handleIntent
            )
            Spacer(modifier = Modifier.weight(1f, fill = true))
            ConfirmTermsText(
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                annotatedString = uiState.termsOfUseText,
                onClick = {

                }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 12.dp),
                colors = ButtonDefaults.buttonColors().copy(
                    containerColor = registrationButton,
                    disabledContainerColor = registrationNotActiveButton,
                    disabledContentColor = additionallyText
                ),
                enabled = uiState.isButtonRegisterEnabled,
                onClick = {
                    handleIntent(RegistrationIntent.OnRegisterButtonClick)
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 4.dp),
                    text = stringResource(R.string.btn_continue)
                )
            }
        }
    }
}


//Собираем все поля для ввода в один компонент, focusRequesters для полей находятся в viewModel
@Composable
private fun TextInputBlock(
    modifier: Modifier = Modifier,
    uiState: RegistrationState,
    handleIntent: (RegistrationIntent) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    Column(
        modifier = modifier
    ) {
        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            value = uiState.sixteenDigitsCode,
            //Проверяем, что вводимые символы, только цифры, ограничиваем 16ю символами
            onValueChange = {
                if (it.isDigitsOnly() && it.length <= 16) {
                    handleIntent(RegistrationIntent.OnSixteenDigitsTextChange(it))
                }
            },
            placeholderText = stringResource(R.string.participants_number),
            focusRequester = uiState.listOfFocusRequesters[0],
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                //Запрашиваем фокус у следующего поля для ввода
                onNext = {
                    uiState.listOfFocusRequesters[1].requestFocus()
                }
            ),
            visualTransformation = GroupedFourNumberVisualTransformation(),
            supportingText = stringResource(R.string.hint_sixteen_digits_number),
            isError = uiState.isSixteenDigitsCodeError,
            onFocusChanged = { hasFocus ->
                if (!hasFocus) {
                    handleIntent(RegistrationIntent.SixteenDigitsFieldLoseFocus)
                }
            }
        )

        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = uiState.code,
            onValueChange = {
                if (it.isDigitsOnly()) {
                    handleIntent(RegistrationIntent.OnCodeTextChange(it))
                }
            },
            placeholderText = stringResource(R.string.code),
            focusRequester = uiState.listOfFocusRequesters[1],
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    uiState.listOfFocusRequesters[2].requestFocus()
                }
            ),
            supportingText = stringResource(R.string.hint_code_from_bank),
            isError = uiState.isCodeError,
            onFocusChanged = { hasFocus ->
                if (!hasFocus) {
                    handleIntent(RegistrationIntent.CodeFieldLoseFocus)
                }

            }
        )

        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = uiState.firstName,
            onValueChange = {
                handleIntent(RegistrationIntent.OnFirstNameTextChange(it))
            },
            placeholderText = stringResource(R.string.first_name),
            focusRequester = uiState.listOfFocusRequesters[2],
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    uiState.listOfFocusRequesters[3].requestFocus()
                }
            ),
            supportingText = stringResource(R.string.hint_first_name)
        )

        DefaultTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            value = uiState.lastName,
            onValueChange = {
                handleIntent(RegistrationIntent.OnLastNameTextChange(it))
            },
            placeholderText = stringResource(R.string.last_name),
            focusRequester = uiState.listOfFocusRequesters[3],
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    //Сбрасываем фокус, убираем клавиатуру
                    focusManager.clearFocus()
                }
            ),
            supportingText = stringResource(R.string.hint_last_name)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    NeuroneTestAppTheme {
        RegistrationScreen(
            uiState = RegistrationState(),
            handleIntent = {}
        )
    }
}