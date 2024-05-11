package com.tfg.supercomparator.ui.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.tfg.supercomparator.R
import com.tfg.supercomparator.ui.theme.Black
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.focusedTextFieldText
import com.tfg.supercomparator.ui.theme.textFieldContainer
import com.tfg.supercomparator.ui.theme.unfocusedTextFieldText

@Composable
fun RegisterTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onIconButtonClick: (Boolean) -> Unit,
    passwordTextField: Boolean,
    textPassWordMode: Boolean,
    label: String
) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Black

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (textPassWordMode) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if (textPassWordMode) KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(
            keyboardType = KeyboardType.Text
        ),
        label = {
            Text(text = label, style = MaterialTheme.typography.labelMedium, color = uiColor)
        },
        colors = TextFieldDefaults.colors(
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
            focusedPlaceholderColor = MaterialTheme.colorScheme.focusedTextFieldText,
            unfocusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
            focusedContainerColor = MaterialTheme.colorScheme.textFieldContainer,
        ),
        trailingIcon = {
            if (passwordTextField) {
                if (textPassWordMode) {
                    IconButton(onClick = { onIconButtonClick(textPassWordMode) }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.eye_slash_svgrepo_com),
                            contentDescription = stringResource(id = R.string.app_name),
                            tint = uiColor
                        )
                    }
                } else {
                    IconButton(onClick = { onIconButtonClick(textPassWordMode) }) {
                        Icon(
                            modifier = Modifier.size(25.dp),
                            painter = painterResource(id = R.drawable.eye_svgrepo_com),
                            contentDescription = stringResource(id = R.string.app_name),
                            tint = uiColor
                        )
                    }
                }
            }
        }
    )
}