package cdr.reportlib.data.interactor

import cdr.reportlib.data.repository.ReportRepository
import cdr.reportlib.models.domain.NewReportDomain
import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Реализация [ReportInteractor]
 *
 * @param reportRepository репозиторий для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal class ReportInteractorImpl(
    private val reportRepository: ReportRepository
) : ReportInteractor {

    override suspend fun saveNewReport(newReport: NewReportDomain) = reportRepository.saveNewReport(newReport)

    override suspend fun getReportInfo(projectId: Int): List<ReportInfoDomain> = reportRepository.getReportInfo(projectId)

    override suspend fun closeProject(projectId: Int) = reportRepository.closeProject(projectId)

    override suspend fun changePaymentStatus(reportId: Int) = reportRepository.changePaymentStatus(reportId)

}