package cdr.identificationlib.presentation.dividing

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.identificationlib.models.presentation.DividingAction
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] для разводящего экрана
 *
 * @author Alexandr Chekunkov
 */
internal class DividingViewModel : ViewModel() {

    val action: SharedFlow<DividingAction> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<DividingAction>()

    /** Запуск экрана авторизации */
    fun launchAuthorizationScreen() = viewModelScope.launch {
        _action.emit(DividingAction.LaunchAuthorizationScreen)
    }

    /** Запуск экрана регистрации */
    fun launchRegistrationScreen() = viewModelScope.launch {
        _action.emit(DividingAction.LaunchRegistrationScreen)
    }
}