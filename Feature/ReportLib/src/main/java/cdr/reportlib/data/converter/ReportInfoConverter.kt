package cdr.reportlib.data.converter

import cdr.reportlib.models.data.ReportInfoResponse
import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Конвертер из [ReportInfoResponse] в [ReportInfoDomain]
 *
 * @author Alexandr Chekunkov
 */
internal fun ReportInfoResponse.toDomain(): ReportInfoDomain = ReportInfoDomain(
    id = this.id ?: -1,
    executor = this.executor ?: "",
    reportName = this.reportName ?: "",
    report = this.report ?: "",
    isPaid = this.isPaid ?: true
)