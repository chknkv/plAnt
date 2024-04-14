package cdr.identificationlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * data-модель, которая используется при авторизации клиента
 *
 * @property login логин клиента
 * @property password пароль клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class AuthorizationRequest(
    @SerializedName("username") val login: String,
    @SerializedName("password") val password: String
) : Parcelable
