package cdr.reportlib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain-модель для создания нового баг-репорта
 *
 * @property projectName название проекта
 * @property reportName название баг-репорта
 * @property report информация баг-репорта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class NewReportDomain(
    val projectName: String,
    val reportName: String,
    val report: String
) : Parcelable
