package cdr.profilelib.models.domain

/**
 * domain-модель с информацией о клиенте с новыми данными
 *
 * @property name новое имя клиента
 * @property surname новая фамилия клиента
 * @property role новая роль клиента (тестировщик или разработчик)
 * @property newPassword новый пароль клиента (если не был отредактирован - null)
 *
 * @author Alexandr Chekunkov
 */
internal data class NewClientInfoDomain(
    val name: String,
    val surname: String,
    val role: NewClientRole,
    val newPassword: String? = null
)

/**
 * domain-модель с новой ролью клиента (тестировщик или разработчик)
 *
 * @author Alexandr Chekunkov
 */
internal enum class NewClientRole {
    /** Тестировщик */
    QA,

    /** Разработчик */
    DEVELOPER
}