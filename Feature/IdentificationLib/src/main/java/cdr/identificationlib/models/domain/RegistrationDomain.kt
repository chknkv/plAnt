package cdr.identificationlib.models.domain

/**
 * domain-модель, которая используется для регистрации клиента
 *
 * @property login логин клиента
 * @property password пароль клиента
 * @property name имя клиента
 * @property surname фамилия клиента
 * @property role роль клиента (разработчик/тестировщик)
 *
 * @author Alexandr Chekunkov
 */
internal data class RegistrationDomain(
    val login: String,
    val password: String,
    val name: String,
    val surname: String,
    val role: RoleDomain
)

/**
 * domain-модель, которая используется определения роли клиента
 *
 * @author Alexandr Chekunkov
 */
internal enum class RoleDomain {
    /** Тестировщик */
    QA,

    /** Разработчик */
    DEVELOPER
}