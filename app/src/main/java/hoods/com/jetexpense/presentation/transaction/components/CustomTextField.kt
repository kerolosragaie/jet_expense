package hoods.com.jetexpense.presentation.transaction.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.core.theme.JetExpenseTheme


@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    labelText: String,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = labelText)
        },
        keyboardOptions = keyboardOptions,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
        ),
        shape = MaterialTheme.shapes.extraLarge,
    )
}


@Preview(showBackground = true)
@Composable
fun PrevCustomTextField() {
    JetExpenseTheme {
        CustomTextField(
            value = "Amount",
            labelText = "Amount",
            onValueChange = {},
        )
    }
}