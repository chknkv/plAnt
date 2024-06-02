package cdr.reportlib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain-модель для создания нового баг-репорта
 *
 * @property projectId уникальный номер проекта
 * @property reportName название баг-репорта
 * @property report информация баг-репорта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class NewReportDomain(
    val projectId: Int,
    val reportName: String,
    val report: String
) : Parcelable
