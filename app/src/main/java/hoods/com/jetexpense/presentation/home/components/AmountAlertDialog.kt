package hoods.com.jetexpense.presentation.home.components

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.core.utils.Category
import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import hoods.com.jetexpense.domain.models.Expense
import hoods.com.jetexpense.domain.models.Income
import hoods.com.jetexpense.presentation.home.viewmodel.HomeViewModel
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountAlertDialog(
    homeViewModel: HomeViewModel = hiltViewModel(),
    onDismiss: (() -> Unit)? = null,
    positiveBttnTitle: String = stringResource(R.string.ok),
    negativeBttnTitle: String = stringResource(R.string.cancel),
    onClickCancel: (() -> Unit)? = null,
    onClickOk: (() -> Unit)? = null,
) {
    val context = LocalContext.current

    var amount by remember {
        mutableDoubleStateOf(0.0)
    }
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var expenseCategory by remember {
        mutableStateOf("")
    }
    val optionsList = listOf(
        stringResource(id = R.string.income),
        stringResource(id = R.string.expense),
    )
    var selectedOption by remember {
        mutableStateOf(optionsList.first())
    }
    val showAmountAlertDialog =
        homeViewModel.showAmountAlertDialog.collectAsStateWithLifecycle().value

    if (!showAmountAlertDialog) {
        title = ""
        description = ""
        expenseCategory = ""
        amount = 0.0
        selectedOption = optionsList.first()
        return
    }

    Dialog(
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        ),
        onDismissRequest = {
            onDismiss?.invoke()
        },
    ) {
        Card(
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.padding(8.dp),
        ) {

            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colorScheme.background),
            ) {
                item {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = stringResource(R.string.enter_the_amount),
                        style = MaterialTheme.typography.titleMedium,
                    )
                }
                items(optionsList) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = it == selectedOption,
                            onClick = {
                                selectedOption = it
                            },
                        )
                        Text(
                            text = it,
                        )
                    }
                }
                item {
                    OutlinedTextField(
                        modifier = Modifier.padding(8.dp),
                        value = title,
                        onValueChange = { title = it },
                        label = {
                            Text(stringResource(R.string.title))
                        },
                    )
                }
                item {
                    OutlinedTextField(
                        modifier = Modifier.padding(8.dp),
                        value = description,
                        onValueChange = { description = it },
                        label = {
                            Text(stringResource(R.string.description))
                        },
                    )
                }
                item {
                    OutlinedTextField(
                        modifier = Modifier.padding(8.dp),
                        value = amount.toString(),
                        onValueChange = { amount = it.toDouble() },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = {
                            Text(stringResource(R.string.amount))
                        },
                    )
                }
                item {
                    AnimatedVisibility(visible = selectedOption.lowercase() == "expense") {
                        LazyRow {
                            items(Category.values()) { category ->
                                InputChip(
                                    modifier = Modifier.padding(8.dp),
                                    selected = category.title == expenseCategory,
                                    onClick = {
                                        expenseCategory = category.title
                                    },
                                    label = {
                                        Text(text = category.title)
                                    },
                                    trailingIcon = {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = painterResource(id = category.iconRes),
                                            contentDescription = null,
                                        )
                                    },
                                )
                            }
                        }
                    }
                }
                item {
                    Row {
                        OutlinedButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1F),
                            onClick = {
                                onClickCancel?.invoke()
                                homeViewModel.showOrHildeAmountAlertDialog()
                            },
                        ) {
                            Text(text = negativeBttnTitle)
                        }

                        Button(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .weight(1F),
                            onClick = {
                                if (title.isBlank() || description.isBlank() || amount <= 0.0) {
                                    Toast.makeText(
                                        context,
                                        "Please fill all the fields.",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                } else {
                                    val date = Calendar.getInstance()

                                    if (selectedOption.lowercase() == "income") {
                                        homeViewModel.insertIncome(
                                            Income(
                                                title = title,
                                                description = description,
                                                incomeAmount = amount,
                                                date = date.time,
                                                entryDate = formatDate(date),
                                            )
                                        )
                                    } else {
                                        homeViewModel.insertExpense(
                                            Expense(
                                                title = title,
                                                description = description,
                                                expenseAmount = amount,
                                                date = date.time,
                                                entryDate = formatDate(date),
                                                category = expenseCategory,
                                            )
                                        )
                                    }
                                    onClickOk?.invoke()
                                    homeViewModel.showOrHildeAmountAlertDialog()
                                }
                            },
                        ) {
                            Text(text = positiveBttnTitle)
                        }
                    }
                }
            }

        }
    }

}

@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun PrevCustomAlertDialog() {
    JetExpenseTheme {
        AmountAlertDialog()
    }
}