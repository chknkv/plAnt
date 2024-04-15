package cdr.identificationlib.data.mapper

import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.RegistrationRequest

/**
 * Маппер для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationMapper {

    /** Авторизация в системе */
    suspend fun signIn(authorizationRequest: AuthorizationRequest): ClientResponse

    /** Регистрация в системе */
    suspend fun signUp(registrationRequest: RegistrationRequest): ClientResponse
}