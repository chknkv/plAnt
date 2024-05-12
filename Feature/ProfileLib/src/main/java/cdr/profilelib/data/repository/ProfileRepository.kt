package cdr.profilelib.data.repository

import cdr.profilelib.models.domain.ClientDomain
import cdr.profilelib.models.domain.NewClientInfoDomain

/**
 * Репозиторий для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal interface ProfileRepository {

    /** Получение информации о пользователе */
    suspend fun getProfileInfo(): ClientDomain

    /** Редактирование информации о пользователе */
    suspend fun editClientInfo(newClientInfo: NewClientInfoDomain)
}