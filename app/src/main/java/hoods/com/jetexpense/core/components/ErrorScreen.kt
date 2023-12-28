package hoods.com.jetexpense.core.components

import hoods.com.jetexpense.R
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.core.theme.JetExpenseTheme

@Composable
fun ErrorScreen(
    message: String?,
    title: String? = null,
    retryFunc: (() -> Unit)? = null,
    showAsScreen: Boolean = true,
) {
    if (showAsScreen) {
        Surface {
            ErrorScreenBody(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                message = message,
                title = title,
                retryFunc = retryFunc,
            )
        }
    } else {
        ErrorScreenBody(
            modifier = Modifier.padding(40.dp),
            message = message,
            title = title,
            retryFunc = retryFunc,
        )
    }
}

@Composable
private fun ErrorScreenBody(
    modifier: Modifier,
    message: String?,
    title: String? = null,
    retryFunc: (() -> Unit)? = null,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title ?: stringResource(id = R.string.error),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.error,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = message ?: stringResource(id = R.string.something_wrong),
            textAlign = TextAlign.Center
        )
        retryFunc?.let {
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { retryFunc() }
            ) {
                Text(
                    text = "Retry"
                )
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Composable
fun ErrorScreenPreview() {
    JetExpenseTheme() {
        ErrorScreen(
            message = "Error",
            showAsScreen = false,
        )
    }
}