package cdr.mainscreenlib.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.mainscreenlib.models.presentation.ProfileState
import cdr.mainscreenlib.models.presentation.ProfileSuccessfulScreen
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] для экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)
        _state.value = ProfileState.Error
    }

    val state: StateFlow<ProfileState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Successful())

    fun fetchProfileData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = ProfileState.Loading

            delay(4500) // Загрузка данных пользователя


            _state.value = ProfileState.Successful(
                data = ProfileSuccessfulScreen(
                    firstName = "Alexandr",
                    lastName = "Chekunkov",
                    username = "user.test",
                    role = "Разработчик"
                )
            )

        }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}