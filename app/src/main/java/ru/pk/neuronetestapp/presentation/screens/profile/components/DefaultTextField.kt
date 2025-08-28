package ru.pk.neuronetestapp.presentation.screens.profile.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.pk.neuronetestapp.R
import ru.pk.neuronetestapp.ui.theme.NeuroneTestAppTheme
import ru.pk.neuronetestapp.ui.theme.additionallyText
import ru.pk.neuronetestapp.ui.theme.clickableImageOrTextButton

@Composable
fun DefaultTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester? = null,
    placeholderText: String,
    supportingText: String = "",
    singleLine: Boolean = true,
    isError: Boolean = false,
    onFocusChanged: (Boolean) -> Unit = {},
    shapeSize: Dp = 12.dp,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column {
        TextField(
            modifier = modifier
                .then(
                    if (focusRequester != null) {
                        Modifier
                            .focusRequester(focusRequester)
                            .onFocusChanged(
                                onFocusChanged = {
                                    onFocusChanged(it.isFocused)
                                }
                            )
                    } else {
                        Modifier
                    }
                )
                .then(
                    if (isError) {
                        Modifier
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.error,
                                shape = RoundedCornerShape(shapeSize)
                            )
                    } else {
                        Modifier
                    }
                ),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            placeholder = {
                Text(
                    text = placeholderText,
                    color = additionallyText
                )
            },
            isError = isError,

            singleLine = singleLine,
            shape = RoundedCornerShape(shapeSize),
            colors = TextFieldDefaults.colors().copy(
                unfocusedContainerColor = clickableImageOrTextButton,
                focusedContainerColor = clickableImageOrTextButton,
                unfocusedTextColor = additionallyText,
                focusedTextColor = additionallyText,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                errorContainerColor = clickableImageOrTextButton,
                errorTextColor = additionallyText
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation
        )
        if (isError){
            Text(
                modifier = Modifier.padding(top = 2.dp),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(R.string.incorrect_data),
                color = MaterialTheme.colorScheme.error
            )
        } else {
            Text(
                modifier = Modifier.padding(top = 2.dp),
                style = MaterialTheme.typography.bodySmall,
                color = additionallyText,
                text = supportingText
            )
        }
    }

}

@Preview
@Composable
private fun DefaultTextFieldPrev() {
    NeuroneTestAppTheme {
        DefaultTextField(
            value = "",
            onValueChange = {},
            placeholderText = "Enter name",
            keyboardOptions = KeyboardOptions.Default,
            keyboardActions = KeyboardActions.Default,
            focusRequester = null,
            isError = true,
        )
    }
}