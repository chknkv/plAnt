package cdr.profilelib.models.domain

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
internal data class ClientInfo(
    val firstName: String = "",
    val lastName: String = "",
    val username: String = "",
    val role: String = ""
)