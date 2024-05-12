package cdr.profilelib.data.interactor

import cdr.profilelib.models.domain.ClientDomain
import cdr.profilelib.models.domain.NewClientInfoDomain

/**
 * Интерактор для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal interface ProfileInteractor {

    /** Получение информации о пользователе */
    suspend fun getProfileInfo(): ClientDomain

    /** Редактирование информации о пользователе */
    suspend fun editClientInfo(newClientInfo: NewClientInfoDomain)
}