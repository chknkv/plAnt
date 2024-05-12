package cdr.profilelib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель с информацией о клиенте
 *
 * @param firstName имя клиента
 * @param lastName фамилия клиента
 * @param username логин клиента
 * @param role роль клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class ClientInfoDomain(
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val role: String = ""
) : Parcelable