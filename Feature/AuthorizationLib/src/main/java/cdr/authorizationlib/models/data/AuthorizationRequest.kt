package cdr.authorizationlib.models.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 *
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class AuthorizationRequest(
    @SerializedName("username") val login: String,
    @SerializedName("password") val password: String
) : Parcelable
