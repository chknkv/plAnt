package cdr.profilelib.data.mapper

import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest

/**
 * Маппер для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal interface ProfileMapper {

    /** Получение информации о пользователе */
    suspend fun getProfileInfo(): ClientResponse

    /** Редактирование информации о пользователе */
    suspend fun editClientInfo(newClientInfo: NewClientInfoRequest)
}