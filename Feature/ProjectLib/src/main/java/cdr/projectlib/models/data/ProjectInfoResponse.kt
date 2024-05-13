package cdr.projectlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель с информацией о проекте
 *
 * @property id уникальный номер проекта
 * @property name название проекта
 * @property author логин автора проекта
 * @property status статус проекта на текущий момент (неизвестно, открыт, в работе, закрыт)
 * @property description описание проекта
 * @property applicationInfo data-модель с информацией о типе устройства
 * @property price цена за выполнение проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
data class ProjectInfoResponse(
    @SerializedName("id") val id: Int? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("author") val author: String? = null,
    @SerializedName("status") val status: ProjectStatusResponse? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("appDto") val applicationInfo: ProjectApplicationInfoData? = null,
    @SerializedName("price") val price: Double? = null
) : Parcelable

/**
 * data-модель с информацией о типе устройства
 *
 * @property operationSystem тип операционной системы мобильного устройства
 * @property url ссылка на скачивание приложения
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
data class ProjectApplicationInfoData(
    @SerializedName("osName") val operationSystem: ProjectOperationSystemData,
    @SerializedName("downloadUrl") val url: String
) : Parcelable

/**
 * Тип операционной системы мобильного устройства
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
enum class ProjectOperationSystemData : Parcelable {

    /** OC Android */
    @SerializedName("ANDROID") ANDROID,

    /** OC iOS */
    @SerializedName("IOS") IOS
}

/**
 * Статусы выполнения проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
enum class ProjectStatusResponse : Parcelable {

    /** Неизвестно */
    @SerializedName("UNKNOWN") UNKNOWN,

    /** Открыт */
    @SerializedName("OPEN") OPEN,

    /** В работе */
    @SerializedName("IN_WORK") IN_WORK,

    /** Закрыт */
    @SerializedName("CLOSED") CLOSED,
}
