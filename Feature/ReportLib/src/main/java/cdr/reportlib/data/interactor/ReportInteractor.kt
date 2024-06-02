package cdr.reportlib.data.interactor

import cdr.reportlib.models.domain.NewReportDomain
import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Интерактор для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal interface ReportInteractor {

    /** Создание нового баг-репорта */
    suspend fun saveNewReport(newReport: NewReportDomain)

    /** Получение баг-репорт информации для какого-то проекта */
    suspend fun getReportInfo(projectId: Int): List<ReportInfoDomain>

    /** Закрытие проекта */
    suspend fun closeProject(projectId: Int)

    /** Условная оплата проекта */
    suspend fun changePaymentStatus(reportId: Int)
}