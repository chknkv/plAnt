package cdr.mainscreenlib.presentation

import androidx.lifecycle.ViewModel
import cdr.mainscreenlib.models.presentation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * [ViewModel] для главного экрана модуля
 *
 * @author Alexandr Chekunkov
 */
internal class PrimaryViewModel : ViewModel() {

    /** Текущий экран, которой отображается (может быть биржей, чатом или профилем) */
    val screen: StateFlow<Screen> get() = _screen.asStateFlow()
    private val _screen = MutableStateFlow(Screen.MARKET)

    /** Изменение текущего экрана */
    fun changeScreen(newScreen: Screen) { _screen.value = newScreen }
}