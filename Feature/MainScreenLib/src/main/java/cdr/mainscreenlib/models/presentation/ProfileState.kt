package cdr.mainscreenlib.models.presentation

/**
 * Состояние экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileState {

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
    value class Successful(val data: ProfileInfo = ProfileInfo()) : ProfileState
}

/**
 * UI-модель, содержащая в себе данные о профиле клиента
 *
 * @param clientInfo UI-модель с информацией о клиента
 * @param projectInfoList список UI-моделей с информацией о проекте клиента
 *
 * @author Alexandr Chekunkov
 */
internal data class ProfileInfo(
    val clientInfo: ClientInfo = ClientInfo(),
    val projectInfoList: List<ClientProjectInfo> = emptyList()
)

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
    val firstName: String = "Unknown",
    val lastName: String = "Unknown",
    val username: String = "Unknown",
    val role: String = "Unknown"
)

/**
 * Модель с информацией о проекте клиента
 *
 * @param id уникальный номер проекта
 * @param name название проекта
 * @param status статус проекта на текущий момент (неизвестно, открыт, в работе, закрыт)
 * @param price цена за выполнение проекта
 * @param isHaveExecutor назначен ли выполнитель проекта
 *
 * @author Alexandr Chekunkov
 */
internal data class ClientProjectInfo(
    val id: Int = -1,
    val name: String = "Unknown",
    val status: ProjectStatus = ProjectStatus.UNKNOWN,
    val price: Int = -1,
    val isHaveExecutor: Boolean = false,
)

/**
 * Статусы выполнения проекта
 *
 * @author Alexandr Chekunkov
 */
internal enum class ProjectStatus{

    /** Неизвестно */
    UNKNOWN,

    /** Открыт */
    OPEN,

    /** В работе */
    IN_WORK,

    /** Закрыт */
    CLOSED,
}