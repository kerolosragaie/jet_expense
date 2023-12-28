package hoods.com.jetexpense.presentation.main.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.presentation.main.Theme
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    var currentTheme: Theme by mutableStateOf(getSelectedTheme())
    fun changeTheme() {
        currentTheme = if (currentTheme == Theme.DARK) Theme.LIGHT else Theme.DARK
        storeSelectedTheme(currentTheme.name)
    }

    private fun storeSelectedTheme(theme: String) {
        stateHandle[THEME_TYPE] = theme
    }

    private fun getSelectedTheme(): Theme =
        when (stateHandle.get<String>(THEME_TYPE)) {
            "DARK" -> {
                Theme.DARK
            }
            "LIGHT" -> {
                Theme.LIGHT
            }
            else -> {
                Theme.SYSTEM
            }
        }

    companion object {
        const val THEME_TYPE: String = "themeType"
    }
}