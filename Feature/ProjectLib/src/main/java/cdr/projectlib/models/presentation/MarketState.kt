package cdr.projectlib.models.presentation

import cdr.projectlib.models.domain.ProjectInfo

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