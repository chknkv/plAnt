package cdr.profilelib.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.profilelib.models.domain.ClientInfoDomain
import cdr.profilelib.models.presentation.main.ProfileAction
import cdr.profilelib.models.presentation.main.ProfileInfo
import cdr.profilelib.models.presentation.main.ProfileState
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.models.domain.ProjectStatusDomain
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    val action: SharedFlow<ProfileAction> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<ProfileAction>()

    fun fetchProfileData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = ProfileState.Loading

            delay(500) // Загрузка данных пользователя

            _state.value = mockedProfileStateData

        }
    }

    fun launchEditScreen() {
        viewModelScope.launch {
            if (_state.value is ProfileState.Successful) {
                val currentData = (_state.value as ProfileState.Successful).data
                _action.emit(ProfileAction.LaunchEditProfile(currentData.clientInfo))
            }
        }
    }

    fun launchInfoAboutProject(projectInfo: ProjectInfoDomain) {
        viewModelScope.launch {
            _action.emit(ProfileAction.LaunchProjectInfoScreen(projectInfo))
        }
    }

    companion object {
        private const val TAG = "ProfileViewModel"
    }
}

private val mockedProfileStateData = ProfileState.Successful(
    data = ProfileInfo(
        clientInfo = ClientInfoDomain(
            firstName = "Alexandr",
            lastName = "Chekunkov",
            username = "@user.test",
            role = "Разработчик"
        ),
        projectInfoList = listOf(
            ProjectInfoDomain(
                id = 1,
                name = "Мобильное приложение «СБОЛ»",
                author = "@user.test",
                status = ProjectStatusDomain.CLOSED,
                price = 15150,
                isHaveExecutor = true,
                executor = "@executor.test",
                description = "Необходимо протестировать несколько экранов"
            ),
            ProjectInfoDomain(
                id = 2,
                name = "Приложение для игры в «Судоку»",
                author = "@user.test",
                status = ProjectStatusDomain.IN_WORK,
                price = 81950,
                isHaveExecutor = true,
                executor = "@executor.test",
                description = "Необходимо протестировать все приложение. Профиль, статистика, задачи, уровни, процесс игры и т.д. Оплачу сразу после проведенной работы, в долгу не останусь. Жду обратной связи, спасибо!"
            ),
            ProjectInfoDomain(
                id = 3,
                name = "Мобильный Банк «Тинькофф»",
                author = "@user.test",
                status = ProjectStatusDomain.OPEN,
                price = 1255,
                isHaveExecutor = false,
                description = "Необходимо протестировать несколько экранов"
            ),
            ProjectInfoDomain(
                id = 4,
                name = "Социальная сеть «ВКонтакте»",
                author = "@user.test",
                status = ProjectStatusDomain.IN_WORK,
                price = 912450,
                isHaveExecutor = true,
                executor = "@executor.test",
                description = "Необходимо протестировать несколько экранов"
            )
        )
    )
)