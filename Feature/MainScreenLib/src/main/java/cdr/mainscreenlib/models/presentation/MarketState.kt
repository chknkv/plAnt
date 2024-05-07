package cdr.mainscreenlib.models.presentation

/**
 * Состояние экрана биржы проектов
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface MarketState {

    /** Загрузка */
    object Loading : MarketState

    /** Ошибка */
    object Error : MarketState

    /**
     * Успех
     *
     * @param data UI-модель, содержащая в себе данные на экране биржы
     */
    @JvmInline
    value class Successful(val data: MarketInfo) : MarketState
}

/**
 * UI-модель, содержащая в себе данные на экране биржы
 *
 * @param projectInfoList список моделей с информацией о проекте
 *
 * @author Alexandr Chekunkov
 */
internal data class MarketInfo(val projectInfoList: List<ProjectInfo>)

/**
 * Модель с информацией о проекте
 *
 * @param id уникальный номер проекта
 * @param name название проекта
 * @param author логин автора проекта
 * @param status статус проекта на текущий момент (неизвестно, открыт, в работе, закрыт)
 * @param price цена за выполнение проекта
 * @param isHaveExecutor назначен ли выполнитель проекта
 *
 * @author Alexandr Chekunkov
 */
internal data class ProjectInfo(
    val id: Int = -1,
    val name: String = "Unknown",
    val author: String = "Unknown",
    val status: ProjectMarketStatus = ProjectMarketStatus.UNKNOWN,
    val price: Int = -1,
    val isHaveExecutor: Boolean = false
)

/**
 * Статусы выполнения проекта
 *
 * @author Alexandr Chekunkov
 */
internal enum class ProjectMarketStatus{

    /** Неизвестно */
    UNKNOWN,

    /** Открыт */
    OPEN,

    /** В работе */
    IN_WORK,

    /** Закрыт */
    CLOSED,
}