package hoods.com.jetexpense.presentation.main.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.jetexpense.presentation.main.Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _currentTheme: MutableStateFlow<Theme> =
        MutableStateFlow(Theme.SYSTEM)
    val currentTheme: StateFlow<Theme> = _currentTheme

    fun changeTheme() {
        _currentTheme.value = if (_currentTheme.value == Theme.DARK) Theme.LIGHT else Theme.DARK
    }
}