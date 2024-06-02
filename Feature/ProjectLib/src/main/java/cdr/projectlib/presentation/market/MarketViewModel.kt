package cdr.projectlib.presentation.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.coreutilslib.token.TokenWorker
import cdr.projectlib.data.interactor.ProjectInteractor
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.models.presentation.MarketAction
import cdr.projectlib.models.presentation.MarketInfo
import cdr.projectlib.models.presentation.MarketState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] для экрана биржы
 *
 * @param projectInteractor интерактор для функционала модуля проектов
 * @param tokenWorker воркер для работы с токеном
 *
 * @author Alexandr Chekunkov
 */
internal class MarketViewModel(
    private val projectInteractor: ProjectInteractor,
    private val tokenWorker: TokenWorker
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)
        _state.value = MarketState.Error
    }

    val state: StateFlow<MarketState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<MarketState>(MarketState.Loading)

    val currentRole: StateFlow<String> get() = _currentRole.asStateFlow()
    private val _currentRole = MutableStateFlow("Unknown")

    val action: SharedFlow<MarketAction> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<MarketAction>()

    fun fetchMarketData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = MarketState.Loading
            val allProjects = projectInteractor.getAllProjects()

            _currentRole.value = tokenWorker.getRole()
            _state.value = MarketState.Successful(
                data = MarketInfo(
                    projectInfoList = allProjects
                )
            )
        }
    }

    fun launchAddProjectScreen() {
        viewModelScope.launch {
            _action.emit(MarketAction.LaunchCreateProjectScreen)
        }
    }

    fun launchInfoAboutProject(projectInfo: ProjectInfoDomain) {
        viewModelScope.launch {
            _action.emit(MarketAction.LaunchProjectInfoScreen(projectInfo))
        }
    }

    companion object {
        private const val TAG = "MarketViewModel"
        const val ROLE_DEVELOPER = "ROLE_DEVELOPER"
    }
}