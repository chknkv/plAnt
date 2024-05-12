package cdr.profilelib.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.profilelib.data.interactor.ProfileInteractor
import cdr.profilelib.models.presentation.main.ProfileAction
import cdr.profilelib.models.presentation.main.ProfileState
import cdr.projectlib.models.domain.ProjectInfoDomain
import kotlinx.coroutines.CoroutineExceptionHandler
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
 * @param profileInteractor интерактор для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileViewModel(
    private val profileInteractor: ProfileInteractor
) : ViewModel() {

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

            val clientInfo = profileInteractor.getProfileInfo()
            _state.value = ProfileState.Successful(
                data = clientInfo
            )
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