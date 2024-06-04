package cdr.reportlib.data.converter

import cdr.reportlib.models.data.NewReportRequest
import cdr.reportlib.models.domain.NewReportDomain

/**
 * Конвертер из [NewReportDomain] в [NewReportRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun NewReportDomain.toRequest(): NewReportRequest = NewReportRequest(
    projectName = this.projectName,
    reportName = this.reportName,
    report = this.report
)