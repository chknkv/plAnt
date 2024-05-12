package cdr.profilelib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель с информацией о клиенте с новыми данными
 *
 * @property name новое имя клиента
 * @property surname новая фамилия клиента
 * @property role новая роль клиента (тестировщик или разработчик)
 * @property newPassword новый пароль клиента (если не был отредактирован - null)
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class NewClientInfoRequest(
    @SerializedName("name") val name: String? = null,
    @SerializedName("surname") val surname: String? = null,
    @SerializedName("role") val role: NewClientRoleRequest? = null,
    @SerializedName("newPassword") val newPassword: String? = null
) : Parcelable

/**
 * data-модель с новой ролью клиента (тестировщик или разработчик)
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal enum class NewClientRoleRequest : Parcelable {
    /** Тестировщик */
    @SerializedName("QA") QA,

    /** Разработчик */
    @SerializedName("DEVELOPER") DEVELOPER
}