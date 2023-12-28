package hoods.com.jetexpense.core.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.core.theme.JetExpenseTheme

@Composable
fun LoadingScreen(showLoadingIndicatorOnly: Boolean = false) {
    if (showLoadingIndicatorOnly) {
        CircularProgressIndicator(
            Modifier.wrapContentSize(Alignment.Center)
        )
    }else{
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                Modifier.wrapContentSize(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    JetExpenseTheme {
        LoadingScreen()
    }
}