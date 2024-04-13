package cdr.authorizationlib.models.domain

/**
 * domain-модель, которая используется при авторизации пользователя
 *
 * @property login логин клиента
 * @property password пароль клиента
 *
 * @author Alexandr Chekunkov
 */
internal data class AuthorizationDomain(
    val login: String,
    val password: String
)