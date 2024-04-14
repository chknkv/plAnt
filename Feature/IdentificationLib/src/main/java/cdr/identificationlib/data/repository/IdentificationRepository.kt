package cdr.identificationlib.data.repository

import cdr.identificationlib.models.domain.AuthorizationDomain
import cdr.identificationlib.models.domain.RegistrationDomain

/**
 * Репозиторий для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationRepository {

    /** Авторизация в системе */
    suspend fun signIn(authorizationDomain: AuthorizationDomain)

    /** Регистрация в системе */
    suspend fun signUp(registrationDomain: RegistrationDomain)
}