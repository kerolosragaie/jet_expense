package hoods.com.jetexpense.presentation.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.presentation.home.components.AccountTotalBalance
import hoods.com.jetexpense.presentation.home.viewmodel.HomeUiState

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
) {

    Scaffold { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding)
        ) {
            item {
                AccountTotalBalance(
                    totalIncome = homeUiState.totalIncome,
                    totalExpense = homeUiState.totalExpense,
                )
            }
        }
    }
}


@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
)
@Composable
fun PrevHomeScreen() {
    JetExpenseTheme {
        HomeScreen(
            homeUiState = HomeUiState(),
        )
    }
}