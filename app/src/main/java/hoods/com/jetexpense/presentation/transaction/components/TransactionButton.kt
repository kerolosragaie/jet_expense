package hoods.com.jetexpense.presentation.transaction.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.presentation.transaction.MockTransactionCallBacks
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionCallBack
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionState

@Composable
fun TransactionButton(
    state: TransactionState,
    transactionCallBack: TransactionCallBack,
    navigateUp: () -> Unit,
) {
    val title =
        if (state.isUpdatingTransaction) stringResource(R.string.update_transaction) else stringResource(
            R.string.add_transaction
        )

    Button(
        enabled = transactionCallBack.isFieldsNotEmpty,
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            when (state.isUpdatingTransaction) {
                true -> {
                    if (state.transactionScreen == Screen.Income) {
                        transactionCallBack.updateIncome()
                    } else {
                        transactionCallBack.updateExpense()
                    }
                }

                false -> {
                    if (state.transactionScreen == Screen.Income) {
                        transactionCallBack.addIncome()
                    } else {
                        transactionCallBack.addExpense()
                    }
                }
            }
            navigateUp.invoke()
        },
    ) {
        Text(text = title)
    }

}


@Preview
@Composable
fun PrevTransactionButton() {
    JetExpenseTheme {
        TransactionButton(
            state = TransactionState(),
            transactionCallBack = MockTransactionCallBacks(),
            navigateUp = {

            },
        )
    }
}

