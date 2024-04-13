package cdr.authorizationlib.data.repository

import cdr.authorizationlib.models.domain.AuthorizationDomain
import cdr.authorizationlib.models.domain.RegistrationDomain

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