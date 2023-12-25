package hoods.com.jetexpense.presentation.transaction.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.theme.JetExpenseTheme
import hoods.com.jetexpense.core.utils.DateFormatter.formatDate
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSelector(
    date: Date,
    onDateChange: (Long?) -> Unit,
) {
    val datePickerState = rememberDatePickerState()
    var openDateDialog by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = Icons.Default.DateRange,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = formatDate(date))
        Spacer(modifier = Modifier.size(4.dp))
        if (openDateDialog) {
            DatePickerDialog(
                onDismissRequest = { openDateDialog = false },
                confirmButton = {
                    Button(onClick = { onDateChange(datePickerState.selectedDateMillis) }) {
                        Text(text = stringResource(R.string.submit))
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openDateDialog = false }) {
                        Text(text = stringResource(R.string.dismiss))
                    }
                },
            ) {
                DatePicker(state = datePickerState)
            }
        }
        IconButton(onClick = { openDateDialog = true }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevDateSelector() {
    JetExpenseTheme {
        DateSelector(
            date = Date(),
            onDateChange = {

            }
        )
    }
}