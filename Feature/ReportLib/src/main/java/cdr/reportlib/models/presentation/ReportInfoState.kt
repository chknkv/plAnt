package cdr.reportlib.models.presentation

import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Состояния экрана создания баг-репорта
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ReportInfoState {

    /** Загрузка */
    object Loading : ReportInfoState

    /** Ошибка при загрузке данных */
    object Error : ReportInfoState

    /**
     * Стандартное состояние экрана, с которым взаимодействует пользователь
     *
     * @property allReports список domain-моделей с информацией о баг-репорте
     */
    @JvmInline
    value class Screen(val allReports: List<ReportInfoDomain>) : ReportInfoState
}