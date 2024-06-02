package cdr.reportlib.data.repository

import cdr.reportlib.models.domain.NewReportDomain
import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Репозиторий для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal interface ReportRepository {

    /** Создание нового баг-репорта */
    suspend fun saveNewReport(newReport: NewReportDomain)

    /** Получение баг-репорт информации для какого-то проекта */
    suspend fun getReportInfo(projectId: Int): List<ReportInfoDomain>

    /** Закрытие проекта */
    suspend fun closeProject(projectId: Int)

    /** Условная оплата проекта */
    suspend fun changePaymentStatus(reportId: Int)
}