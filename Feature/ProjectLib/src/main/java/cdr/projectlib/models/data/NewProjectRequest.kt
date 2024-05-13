package cdr.projectlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель с информацией о новом проекте
 *
 * @property name навзвание нового проекта
 * @property price цена нового проекта
 * @property description описание нового проекта
 * @property applicationInfo data-модель с информацией о типе устройства
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class NewProjectRequest(
    @SerializedName("name") val name: String? = null,
    @SerializedName("price") val price: Double? = null,
    @SerializedName("description") val description: String? = null,
    @SerializedName("appDto") val applicationInfo: ProjectApplicationInfoData? = null
) : Parcelable