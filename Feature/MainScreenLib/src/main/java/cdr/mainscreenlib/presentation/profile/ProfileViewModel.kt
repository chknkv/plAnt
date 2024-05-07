package cdr.mainscreenlib.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.mainscreenlib.models.presentation.ClientInfo
import cdr.mainscreenlib.models.presentation.ClientProjectInfo
import cdr.mainscreenlib.models.presentation.ProfileInfo
import cdr.mainscreenlib.models.presentation.ProfileState
import cdr.mainscreenlib.models.presentation.ProjectStatus
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
    private val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)

    fun fetchProfileData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = ProfileState.Loading

            delay(500) // Загрузка данных пользователя

            _state.value = mockedProfileStateData

        }
    }

    fun launchEditScreen() {
        Logger.i(TAG, "launch edit screen")
    }

    fun launchInfoAboutProject(projectId: Int) {
        Logger.i(TAG, "launch info about project with id $projectId")
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}

private val mockedProfileStateData = ProfileState.Successful(
    data = ProfileInfo(
        clientInfo = ClientInfo(
            firstName = "Alexandr",
            lastName = "Chekunkov",
            username = "@user.test",
            role = "Разработчик"
        ),
        projectInfoList = listOf(
            ClientProjectInfo(
                id = 1,
                name = "Мобильное приложение «СБОЛ»",
                status = ProjectStatus.CLOSED,
                price = 15150,
                isHaveExecutor = true
            ),
            ClientProjectInfo(
                id = 2,
                name = "Приложение для игры в Судоку",
                status = ProjectStatus.UNKNOWN,
                price = 81950,
                isHaveExecutor = false
            ),
            ClientProjectInfo(
                id = 3,
                name = "Мобильный Банк «Тинькофф»",
                status = ProjectStatus.OPEN,
                price = 1255,
                isHaveExecutor = false
            ),
            ClientProjectInfo(
                id = 4,
                name = "Социальная сеть «ВКонтакте»",
                status = ProjectStatus.IN_WORK,
                price = 912450,
                isHaveExecutor = true
            )
        )
    )
)