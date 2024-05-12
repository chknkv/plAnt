package cdr.profilelib.models.domain

/**
 * domain-модель с информацией о клиенте с новыми данными
 *
 * @param name новое имя клиента
 * @param surname новая фамилия клиента
 * @param role новая роль клиента (тестировщик или разработчик)
 * @param newPassword новый пароль клиента (если не был отредактирован - null)
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