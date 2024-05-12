package cdr.profilelib.models.presentation.main

import cdr.profilelib.models.domain.ClientDomain

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
    value class Successful(val data: ClientDomain) : ProfileState
}