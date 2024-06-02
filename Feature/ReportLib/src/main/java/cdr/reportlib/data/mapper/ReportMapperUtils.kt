package cdr.reportlib.data.mapper

import cdr.reportlib.models.data.ReportInfoResponse
import kotlinx.coroutines.delay

/**
 * Метод [getMockedAllReportInfo] используется для отладки работы с баг-репортами при отсутсвии связи с удаленным сервисов.
 *
 * @return список моккнутых data-модель с информацией о проекте
 * @author Alexandr Chekunkov
 */
internal suspend fun getMockedAllReportInfo(): List<ReportInfoResponse> {
    delay(1500)
    return MOCKED_ALL_REPORT_INFO
}

private val MOCKED_ALL_REPORT_INFO = listOf(
    ReportInfoResponse(
        executor = "test_qa",
        reportName = "Ошибка с авторизацией",
        report = "Произошла ошибка при авторизации в мобильном приложении",
        isPaid = true
    ),
    ReportInfoResponse(
        executor = "test_qa",
        reportName = "Проблема ввода данных на экране профиля",
        report = "Произошла ошибка при авторизации в мобильном приложении",
        isPaid = true
    ),
    ReportInfoResponse(
        executor = "test_qa",
        reportName = "Приложение вылетает на финальном экране",
        report = "Произошла ошибка при авторизации в мобильном приложении",
        isPaid = false
    ),
    ReportInfoResponse(
        executor = "test_qa",
        reportName = "Плохо отраватывает анимация перехода между экранами ",
        report = "Произошла ошибка при авторизации в мобильном приложении",
        isPaid = false
    )
)