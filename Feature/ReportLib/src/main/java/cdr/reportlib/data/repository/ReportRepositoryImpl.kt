package cdr.reportlib.data.repository

import cdr.reportlib.data.converter.toDomain
import cdr.reportlib.data.converter.toRequest
import cdr.reportlib.data.mapper.ReportMapper
import cdr.reportlib.models.domain.NewReportDomain
import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Реализация [ReportRepository]
 *
 * @param reportMapper маппер для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal class ReportRepositoryImpl(
    private val reportMapper: ReportMapper
) : ReportRepository {

    override suspend fun saveNewReport(newReport: NewReportDomain) = reportMapper.saveNewReport(newReport.toRequest())

    override suspend fun getReportInfo(projectName: String): List<ReportInfoDomain> {
        val allReportsInfo = reportMapper.getReportInfo(projectName)
        return allReportsInfo.map { reportInfoResponse -> reportInfoResponse.toDomain() }
    }

    override suspend fun closeProject(projectName: String) = reportMapper.closeProject(projectName)

    override suspend fun changePaymentStatus(reportId: Int) = reportMapper.changePaymentStatus(reportId)
}