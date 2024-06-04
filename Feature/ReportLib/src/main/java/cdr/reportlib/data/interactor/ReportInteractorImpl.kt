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

    override suspend fun getReportInfo(projectName: String): List<ReportInfoDomain> = reportRepository.getReportInfo(projectName)

    override suspend fun closeProject(projectName: String) = reportRepository.closeProject(projectName)

    override suspend fun changePaymentStatus(reportId: Int) = reportRepository.changePaymentStatus(reportId)

}