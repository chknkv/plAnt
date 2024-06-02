package cdr.reportlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель для создания нового баг-репорта
 *
 * @property projectId уникальный номер проекта
 * @property reportName название баг-репорта
 * @property report информация баг-репорта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class NewReportRequest(
    @SerializedName("projectId") val projectId: Int,
    @SerializedName("reportName") val reportName: String,
    @SerializedName("report") val report: String
) : Parcelable