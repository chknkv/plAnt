package cdr.reportlib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain-модель с информацией о баг-репорте
 *
 * @property executor тестировщик, который нашел и завел баг-репорт
 * @property reportName название баг-репорта
 * @property report описание баг-репорта
 * @property isPaid оплачен ли клиентом данный баг-репорт
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class ReportInfoDomain(
    val executor: String,
    val reportName: String,
    val report: String,
    val isPaid: Boolean
) : Parcelable
