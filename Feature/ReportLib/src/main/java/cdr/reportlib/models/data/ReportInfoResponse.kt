package cdr.reportlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель с информацией о баг-репорте
 *
 * @property executor тестировщик, который нашел и завел баг-репорт
 * @property reportName название баг-репорта
 * @property report описание баг-репорта
 * @property isPaid оплачен ли клиентом данный баг-репорт
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class ReportInfoResponse(
    @SerializedName("executor") val executor: String? = null,
    @SerializedName("reportName") val reportName: String? = null,
    @SerializedName("report") val report: String? = null,
    @SerializedName("isPaid") val isPaid: Boolean? = null
) : Parcelable
