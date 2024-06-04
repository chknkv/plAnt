package cdr.reportlib.presentation.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.coreutilslib.logs.Logger
import cdr.reportlib.data.interactor.ReportInteractor
import cdr.reportlib.models.presentation.ReportInfoState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана с информацией об ошибках
 *
 * @param reportInteractor рнтерактор для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal class ReportInfoViewModel(
    private val reportInteractor: ReportInteractor
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)
        _state.value = ReportInfoState.Error
    }

    val state: StateFlow<ReportInfoState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<ReportInfoState>(ReportInfoState.Loading)

    fun fetchProjectReport(projectName: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = ReportInfoState.Loading
            val allReports = reportInteractor.getReportInfo(projectName)

            _state.value = ReportInfoState.Screen(allReports)
        }
    }

    fun closeProject(projectName: String, onFinish: () -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            _state.value = ReportInfoState.Loading
            reportInteractor.closeProject(projectName)

            onFinish.invoke()
        }
    }

    companion object {
        private const val TAG = "ReportInfoViewModel"
    }
}