package cdr.reportlib.data.mapper

import cdr.reportlib.models.data.NewReportRequest
import cdr.reportlib.models.data.ReportInfoResponse

/**
 * Маппер для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal interface ReportMapper {

    /** Создание нового баг-репорта */
    suspend fun saveNewReport(newReport: NewReportRequest)

    /** Получение баг-репорт информации для какого-то проекта */
    suspend fun getReportInfo(projectId: Int): List<ReportInfoResponse>

    /** Закрытие проекта */
    suspend fun closeProject(projectId: Int)

    /** Условная оплата проекта */
    suspend fun changePaymentStatus(reportId: Int)
}
