package cdr.mainscreenlib.presentation.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.mainscreenlib.models.presentation.MarketAction
import cdr.mainscreenlib.models.presentation.MarketInfo
import cdr.mainscreenlib.models.presentation.MarketState
import cdr.mainscreenlib.models.presentation.ProjectInfo
import cdr.mainscreenlib.models.presentation.ProjectMarketStatus
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
 * [ViewModel] для экрана биржы
 *
 * @author Alexandr Chekunkov
 */
internal class MarketViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)
        _state.value = MarketState.Error
    }

    val state: StateFlow<MarketState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<MarketState>(MarketState.Loading)

    val action: SharedFlow<MarketAction> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<MarketAction>()

    fun fetchMarketData() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = MarketState.Loading

            delay(500) // Загрузка данных пользователя

            _state.value = mockedMarketStateData
        }
    }

    fun launchAddProjectScreen() {
        viewModelScope.launch {
            _action.emit(MarketAction.LaunchCreateProjectScreen)
        }
    }

    fun launchInfoAboutProject(id: Int) {
        viewModelScope.launch {
            _action.emit(MarketAction.LaunchProjectInfoScreen(id))
        }
    }

    companion object {
        private const val TAG = "MarketViewModel"
    }
}

private val mockedMarketStateData = MarketState.Successful(
    data = MarketInfo(
        projectInfoList = listOf(
            ProjectInfo(
                id = 4,
                name = "Месенджер «Телеграмм»",
                author = "@sharikov",
                status = ProjectMarketStatus.OPEN,
                price = 123098,
                isHaveExecutor = false
            ),
            ProjectInfo(
                id = 5,
                name = "Мобильное приложение «Шахматы»",
                author = "@sharikov",
                status = ProjectMarketStatus.OPEN,
                price = 5450,
                isHaveExecutor = false
            ),
            ProjectInfo(
                id = 6,
                name = "Приложение для ПВЗ «OZON»",
                author = "@sobakov.best",
                status = ProjectMarketStatus.OPEN,
                price = 124444,
                isHaveExecutor = false
            ),
            ProjectInfo(
                id = 1,
                name = "Мобильное приложение «СБОЛ»",
                author = "@user.test",
                status = ProjectMarketStatus.CLOSED,
                price = 15150,
                isHaveExecutor = true
            ),
            ProjectInfo(
                id = 2,
                name = "Приложение для игры в Судоку",
                author = "@user.test",
                status = ProjectMarketStatus.UNKNOWN,
                price = 81950,
                isHaveExecutor = false
            ),
            ProjectInfo(
                id = 3,
                name = "Мобильный Банк «Тинькофф»",
                author = "@user.test",
                status = ProjectMarketStatus.OPEN,
                price = 1255,
                isHaveExecutor = false
            ),
            ProjectInfo(
                id = 4,
                name = "Социальная сеть «ВКонтакте»",
                author = "@user.test",
                status = ProjectMarketStatus.IN_WORK,
                price = 912450,
                isHaveExecutor = true
            )
        )
    )
)