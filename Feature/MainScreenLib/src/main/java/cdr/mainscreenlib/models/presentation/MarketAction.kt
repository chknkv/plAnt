package cdr.mainscreenlib.models.presentation

/**
 * Экшены для экрана биржы
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface MarketAction {

    /** Запуск экрана информации о проекте */
    @JvmInline
    value class LaunchProjectInfoScreen(val projectId: Int) : MarketAction

    /** Запуск экрана создания нового проекта */
    object LaunchCreateProjectScreen : MarketAction
}