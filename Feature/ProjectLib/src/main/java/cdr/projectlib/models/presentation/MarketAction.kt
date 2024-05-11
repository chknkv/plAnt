package cdr.projectlib.models.presentation

import cdr.projectlib.models.domain.ProjectInfoDomain

/**
 * Экшены для экрана биржы
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface MarketAction {

    /** Запуск экрана информации о проекте */
    @JvmInline
    value class LaunchProjectInfoScreen(val projectInfo: ProjectInfoDomain) : MarketAction

    /** Запуск экрана создания нового проекта */
    object LaunchCreateProjectScreen : MarketAction
}