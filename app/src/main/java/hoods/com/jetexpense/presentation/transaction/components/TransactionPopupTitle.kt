package hoods.com.jetexpense.presentation.transaction.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.presentation.transaction.MockTransactionCallBacks
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionCallBack
import hoods.com.jetexpense.presentation.transaction.viewmodel.TransactionState


@Composable
fun TransactionPopupTitle(
    icon: Int,
    state: TransactionState,
    transactionCallBack: TransactionCallBack,
    transactionScreenList: List<Screen>,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = state.transactionScreen.pageTitle)
        Spacer(modifier = Modifier.width(6.dp))
        IconButton(
            onClick = { transactionCallBack.onOpenDialog(!state.openDialog) }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }
        //Show or hide popup
        if (!state.openDialog) {
            Popup(
                onDismissRequest = {
                    transactionCallBack.onOpenDialog(!state.openDialog)
                }
            ) {
                Surface(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Column {
                        for (screen in transactionScreenList) {
                            Text(
                                text = screen.pageTitle,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .clickable {
                                        transactionCallBack.onScreenTypeChange(screen)
                                        transactionCallBack.onOpenDialog(!state.openDialog)
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevTransactionPopupTitle() {
    JetExpenseTheme {
        TransactionPopupTitle(
            icon = R.drawable.ic_income_dollar,
            state = TransactionState(),
            transactionCallBack = MockTransactionCallBacks(),
            transactionScreenList = listOf(Screen.Income, Screen.Expense),
        )
    }
}