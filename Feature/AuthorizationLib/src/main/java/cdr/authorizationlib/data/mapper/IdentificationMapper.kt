package cdr.authorizationlib.data.mapper

import cdr.authorizationlib.models.data.AuthorizationRequest

/**
 * Маппер для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationMapper {

    /** Авторизация в системе */
    suspend fun signIn(authorizationRequest: AuthorizationRequest)
}