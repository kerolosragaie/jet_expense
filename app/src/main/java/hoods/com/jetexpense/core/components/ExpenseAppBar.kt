package hoods.com.jetexpense.core.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.R
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.theme.JetExpenseTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseAppBar(
    @DrawableRes icon: Int = R.drawable.ic_switch_off,
    title: String,
    onSwitchClick: () -> Unit,
    onNavigateUp: () -> Unit,
) {

    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            AnimatedVisibility(
                visible = title != Screen.Home.pageTitle
            ) {
                IconButton(onClick = onNavigateUp) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            AnimatedContent(
                targetState = icon,
                label = "",
            ) { iconRes ->
                IconButton(onClick = onSwitchClick) {
                    Icon(
                        painter = painterResource(id = iconRes),
                        contentDescription = null,
                    )
                }
            }
        },
    )

}


@Preview
@Composable
fun PrevExpenseAppBar() {
    JetExpenseTheme {
        ExpenseAppBar(
            title = "Income",
            onSwitchClick = {},
            onNavigateUp = {},
        )
    }
}