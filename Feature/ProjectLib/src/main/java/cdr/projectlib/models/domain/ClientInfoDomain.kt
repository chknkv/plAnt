package cdr.projectlib.models.domain

/**
 * Информация о клиенте, которая используется для правильной работы с UI
 *
 * @property username логин текущего клиента
 * @property role роль текущего клиента (разработчик или тестировщик)
 *
 * @author Alexandr Chekunkov
 */
internal data class ClientInfoDomain(
    val username: String = "Unknown",
    val role: String = "Unknown"
)