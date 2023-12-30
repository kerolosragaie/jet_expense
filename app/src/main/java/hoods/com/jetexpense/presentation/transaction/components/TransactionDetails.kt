package hoods.com.jetexpense.presentation.transaction.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.core.utils.Category
import hoods.com.jetexpense.presentation.transaction.MockTransactionCallBacks
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionCallBack
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionUiState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetails(
    state: TransactionUiState,
    transactionCallBack: TransactionCallBack,
    isExpenseTransaction: Boolean,
) {
    Column {
        CustomTextField(
            value = state.title,
            labelText = stringResource(R.string.transaction_title),
            onValueChange = transactionCallBack::onTitleChange,
        )
        Spacer(modifier = Modifier.size(12.dp))
        CustomTextField(
            value = state.amount,
            labelText = stringResource(id = R.string.amount),
            onValueChange = transactionCallBack::onAmountChange,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.size(12.dp))
        DateSelector(date = state.date, onDateChange = transactionCallBack::onDateChange)
        CustomTextField(
            value = state.description,
            labelText = stringResource(id = R.string.description),
            onValueChange = transactionCallBack::onDescriptionChange,
        )
        AnimatedVisibility(visible = isExpenseTransaction) {
            LazyRow {
                items(Category.values()) { category ->
                    InputChip(
                        modifier = Modifier.padding(8.dp),
                        selected = category == state.category,
                        label = {
                            Text(text = category.title)
                        },
                        onClick = {
                            transactionCallBack.onCategoryChange(category)
                        },
                        trailingIcon = {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = category.iconRes),
                                contentDescription = null,
                            )
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevTransactionScreen() {
    JetExpenseTheme {
        TransactionDetails(
            state = TransactionUiState(),
            isExpenseTransaction = true,
            transactionCallBack = MockTransactionCallBacks(),
        )
    }
}