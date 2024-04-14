package cdr.identificationlib.models.domain

/**
 * domain-модель, описывающая сущность клиента после авторизации или регистрации
 *
 * @property client domain-модель с информацией об авторизированным клиенте
 * @property token токен
 *
 * @author Alexandr Chekunkov
 */
data class ClientDomain(
    val client: ClientInfo,
    val token: String
)

/**
 * domain-модель с информацией об авторизированным клиенте
 *
 * @property login логин клиента
 * @property name имя клиента
 * @property surname фамилия клиента
 * @property role data-модель с ролью авторизованного клиента
 *
 * @author Alexandr Chekunkov
 */
data class ClientInfo(
    val login: String,
    val name: String,
    val surname: String,
    val role: ClientRole
)

/**
 * domain-модель с ролью авторизованного клиента
 *
 * @author Alexandr Chekunkov
 */
enum class ClientRole {
    /** Тестировщик */
    QA,

    /** Разработчик */
    DEVELOPER
}