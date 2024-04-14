package cdr.identificationlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель, которая используется при регистрации клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class RegistrationRequest(
    @SerializedName("username") val login: String,
    @SerializedName("password") val password: String,
    @SerializedName("firstName") val name: String,
    @SerializedName("lastName") val surname: String,
    @SerializedName("role") val role: RoleRequest
) : Parcelable

/**
 * data-модель, которая используется определения роли клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal enum class RoleRequest : Parcelable {
    /** Тестировщик */
    @SerializedName("ROLE_QA") QA,

    /** Разработчик */
    @SerializedName("ROLE_DEVELOPER") DEVELOPER
}