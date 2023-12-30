package hoods.com.jetexpense.presentation.main.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.presentation.main.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
) : ViewModel() {

    private val _currentTheme: MutableStateFlow<Theme> by lazy { MutableStateFlow(getSelectedTheme()) }
    val currentTheme: StateFlow<Theme> by lazy { _currentTheme }
    fun changeTheme() {
        _currentTheme.value = if ( _currentTheme.value == Theme.DARK) Theme.LIGHT else Theme.DARK
        storeSelectedTheme(currentTheme.value.name)
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