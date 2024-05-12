package cdr.profilelib.models.data

import android.os.Parcelable
import cdr.projectlib.models.data.ProjectInfoResponse
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-моделей с информацией о клиенте и его проектах
 *
 * @property clientInfo data-модель с информацией о клиенте
 * @property clientProjects список моделей data-моделей с информацией о проектах
 *
 * @author Alexandr Chekunkov
 */
internal data class ClientResponse(
    val clientInfo: ClientInfoResponse,
    val clientProjects: List<ProjectInfoResponse>
)

/**
 * data-модель с информацией о клиенте
 *
 * @property firstName имя клиента
 * @property lastName фамилия клиента
 * @property username логин клиента
 * @property role роль клиента
 *
 * @author Alexandr Chekunkov
 */
internal data class ClientInfoResponse(
    @SerializedName("firstName") val firstName: String? = null,
    @SerializedName("lastName") val lastName: String? = null,
    @SerializedName("username") val username: String? = null,
    @SerializedName("role") val role: ClientInfoRoleResponse? = null
)

/**
 * data-модель с новой ролью клиента (тестировщик или разработчик)
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal enum class ClientInfoRoleResponse : Parcelable {
    /** Тестировщик */
    @SerializedName("QA")
    QA,

    /** Разработчик */
    @SerializedName("DEVELOPER")
    DEVELOPER
}

