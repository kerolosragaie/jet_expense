package hoods.com.jetexpense.presentation.transaction

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.presentation.transaction.components.TransactionButton
import hoods.com.jetexpense.presentation.transaction.components.TransactionDetails
import hoods.com.jetexpense.presentation.transaction.components.TransactionPopupTitle
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionAssistedFactory
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionCallBack
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionUiState
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionViewModel
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionViewModelFactory


@Composable
fun TransactionScreen(
    modifier: Modifier,
    transactionId: Int,
    transactionType: String,
    assistedFactory: TransactionAssistedFactory,
    navigateUp: () -> Unit,
) {
    val viewModel = viewModel(
        modelClass = TransactionViewModel::class.java,
        factory = TransactionViewModelFactory(
            id = transactionId,
            transactionType = transactionType,
            assistedFactory = assistedFactory,
        )
    )

    TransactionScreen(
        modifier = modifier,
        state = viewModel.state.collectAsStateWithLifecycle().value,
        transactionCallBack = viewModel,
        navigateUp = navigateUp,
    )
}

@Composable
private fun TransactionScreen(
    modifier: Modifier,
    state: TransactionUiState,
    transactionCallBack: TransactionCallBack,
    navigateUp: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val transactionScreenList = listOf(Screen.Income, Screen.Expense)
    val isExpenseTransaction = state.transactionScreen == Screen.Expense
    val icon =
        if (isExpenseTransaction) R.drawable.ic_expense_dollar else R.drawable.ic_income_dollar

    Column(
        modifier
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical,
            )
    ) {
        TransactionPopupTitle(
            icon = icon,
            state = state,
            transactionCallBack = transactionCallBack,
            transactionScreenList = transactionScreenList,
        )
        Spacer(modifier = Modifier.height(12.dp))
        TransactionDetails(
            state = state,
            transactionCallBack = transactionCallBack,
            isExpenseTransaction = isExpenseTransaction,
        )
        Spacer(modifier = Modifier.height(12.dp))
        TransactionButton(
            state = state,
            transactionCallBack = transactionCallBack,
            navigateUp = navigateUp,
        )
    }

}


@Preview(showSystemUi = true)
@Composable
fun PrevTransactionScreen() {
    JetExpenseTheme {
        TransactionScreen(
            modifier = Modifier,
            state = TransactionUiState(),
            transactionCallBack = MockTransactionCallBacks(),
            navigateUp = {},
        )
    }
}