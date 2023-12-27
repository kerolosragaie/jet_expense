package hoods.com.jetexpense.core.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import hoods.com.jetexpense.core.navigation.Screen
import hoods.com.jetexpense.core.theme.JetExpenseTheme

@Composable
fun CustomButtonBar(
    allScreens: List<Screen>,
    onTabSelected: (Screen) -> Unit,
    selectedTab: Screen,
    onFabClicked: () -> Unit,
) {
    BottomAppBar(
        floatingActionButton = {
            FloatingActionBttn(
                onClick = onFabClicked,
                selectedTab = selectedTab,
            )
        },
        actions = {
            allScreens.forEach { screen ->
                TabButton(
                    text = screen.pageTitle,
                    icon = screen.iconResId,
                    onSelected = {
                        onTabSelected(screen)
                    },
                    selected = selectedTab == screen,
                )
            }
        },
    )
}

@Preview
@Composable
fun PrevCustomButtonBar() {
    val screens = listOf(Screen.Home, Screen.Income, Screen.Expense)
    JetExpenseTheme {
        CustomButtonBar(
            allScreens = screens,
            onTabSelected = { _ -> },
            selectedTab = screens.first(),
            onFabClicked = {},
        )
    }
}