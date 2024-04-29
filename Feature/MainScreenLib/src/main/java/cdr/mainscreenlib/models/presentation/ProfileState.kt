package cdr.mainscreenlib.models.presentation

/**
 * Состояние экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileState  {

    /** Загрузка */
    object Loading : ProfileState

    /** Ошибка */
    object Error : ProfileState

    /**
     * Успех
     *
     * @param data UI-модель, содержащая в себе данные о профиле
     */
    @JvmInline
    value class Successful(val data: ProfileSuccessfulScreen = ProfileSuccessfulScreen()) : ProfileState
}

/**
 * UI-модель, содержащая в себе данные о профиле клиента
 *
 * @param firstName имя клиента
 * @param lastName фамилия клиента
 * @param username логин клиента
 * @param role роль клиента
 *
 * @author Alexandr Chekunkov
 */
internal data class ProfileSuccessfulScreen(
    val firstName: String ="Unknown",
    val lastName: String = "Unknown",
    val username: String = "Unknown",
    val role: String = "Unknown"
)