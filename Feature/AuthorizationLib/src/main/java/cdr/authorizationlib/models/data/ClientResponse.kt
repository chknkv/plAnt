package cdr.authorizationlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель ответа от сервера при авторизации
 *
 * @property client data-модель с информацией об авторизированным клиенте
 * @property token токен
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class ClientResponse(
    @SerializedName("user") val client: ClientInfoResponse,
    @SerializedName("jwtToken")  val token: String
) : Parcelable

/**
 * data-модель с информацией об авторизированным клиенте
 *
 * @property login логин клиента
 * @property name имя клиента
 * @property surname фамилия клиента
 * @property role data-модель с ролью авторизованного клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class ClientInfoResponse(
    @SerializedName("username") val login: String,
    @SerializedName("firstName") val name: String,
    @SerializedName("lastName") val surname: String,
    @SerializedName("role") val role: RoleResponse
) : Parcelable

/**
 * data-модель с ролью авторизованного клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal enum class RoleResponse : Parcelable {
    /** Тестировщик */
    @SerializedName("ROLE_QA") QA,

    /** Разработчик */
    @SerializedName("ROLE_DEVELOPER") DEVELOPER

}