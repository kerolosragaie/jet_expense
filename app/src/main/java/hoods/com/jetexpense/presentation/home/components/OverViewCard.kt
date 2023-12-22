package hoods.com.jetexpense.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.R

const val SHOWN_ITEMS = 3

@Composable
fun <T> OverViewCard(
    title: String,
    amount: Float,
    onClickSeeAll: () -> Unit,
    values: (T) -> Float,
    colors: (T) -> Color,
    data: List<T>,
    row: @Composable (T) -> Unit,
) {
    Card {
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(12.dp),
            )
            OverViewDivider(
                data = data,
                values = values,
                colors = colors,
            )
            Column(
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 4.dp)
            ) {
                data.takeLast(SHOWN_ITEMS).forEach {
                    row(it)
                }
            }
            TextButton(
                modifier = Modifier
                    .height(44.dp)
                    .fillMaxWidth()
                    .clearAndSetSemantics {
                        contentDescription = "All $title"
                    },
                onClick = onClickSeeAll,
            ) {
                Text(
                    text = stringResource(R.string.see_all).uppercase()
                )
            }
        }
    }
}


@Composable
private fun <T> OverViewDivider(
    data: List<T>,
    values: (T) -> Float,
    colors: (T) -> Color,
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        data.forEach { item ->
            Spacer(
                modifier = Modifier
                    .weight(values(item))
                    .height(1.dp)
                    .background(colors(item))
            )
        }
    }
}


@Preview
@Composable
fun PrevDataCard() {

}