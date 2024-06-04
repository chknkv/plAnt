package cdr.reportlib.presentation.report

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.corecompose.textfield.TextFieldCardStyles
import cdr.coreutilslib.logs.Logger
import cdr.reportlib.data.interactor.ReportInteractor
import cdr.reportlib.models.domain.NewReportDomain
import cdr.reportlib.models.presentation.ReportAction
import cdr.reportlib.models.presentation.ReportScreen
import cdr.reportlib.models.presentation.ReportState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel для экрана создания репорта об найденных ошибках
 *
 * @param reportInteractor рнтерактор для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal class ReportViewModel(
    private val reportInteractor: ReportInteractor
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)

        currentData?.let { currentData ->
            _state.value = ReportState.Screen(data = currentData.copy(isShowErrorAlert = true))
        }
    }

    val state: StateFlow<ReportState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<ReportState>(ReportState.Screen())

    /** Действие для показа snackbar на экране */
    val action: SharedFlow<ReportAction> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<ReportAction>()

    /** Текущее данные, отображаемые на экране */
    private var currentData: ReportScreen? = null

    fun sendReport(
        projectName: String,
        onFinish: () -> Unit
    ) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val currentState = _state.value
            if (currentState is ReportState.Screen) {
                currentData = currentState.data

                currentData?.let { currentData ->
                    if (checkIsNotBlank(currentData)) {
                        _state.value = ReportState.Loading

                        val newReport = NewReportDomain(
                            projectName = projectName,
                            reportName = currentData.reportName.text.text,
                            report = currentData.report.text.text
                        )
                        reportInteractor.saveNewReport(newReport)

                        onFinish.invoke()
                    }
                }
            }
        }
    }

    /** Проверка на пустые поля */
    private suspend fun checkIsNotBlank(currentData: ReportScreen): Boolean {
        val blankReport = currentData.report.text.text.isBlank()
        val blankReportName = currentData.reportName.text.text.isBlank()

        return if (blankReport || blankReportName) {
            _state.value = ReportState.Screen(
                data = currentData.copy(
                    reportName = currentData.report.copy(
                        style = if (blankReportName) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    ),
                    report = currentData.report.copy(
                        style = if (blankReport) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    )
                )
            )

            _action.emit(ReportAction.EmptyFields)
            false
        } else true
    }

    fun handleNewReportName(newReportName: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ReportState.Screen) {
            val currentData = currentState.data

            _state.value = ReportState.Screen(
                data = currentData.copy(
                    reportName = currentData.reportName.copy(
                        text = if (newReportName.text.length <= MAX_CHARACTERS) newReportName else currentData.reportName.text,
                        subtitleVisibility = newReportName.text.length >= MAX_CHARACTERS,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    fun handleNewReport(newReport: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ReportState.Screen) {
            val currentData = currentState.data

            _state.value = ReportState.Screen(
                data = currentData.copy(
                    report = currentData.report.copy(
                        text = if (newReport.text.length <= MAX_CHARACTERS) newReport else currentData.report.text,
                        subtitleVisibility = newReport.text.length >= MAX_CHARACTERS,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    /** Сокрытие AlertDialog с UI */
    fun dismissAlertDialog() {
        val currentState = _state.value
        if (currentState is ReportState.Screen) {
            val currentData = currentState.data
            _state.value = ReportState.Screen(
                data = currentData.copy(
                    isShowErrorAlert = false
                )
            )
        }
    }

    companion object {
        private const val TAG = "ReportViewModel"
        private const val MAX_CHARACTERS = 2000
    }
}