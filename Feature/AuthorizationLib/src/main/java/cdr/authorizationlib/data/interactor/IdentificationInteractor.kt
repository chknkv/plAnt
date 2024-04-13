package cdr.authorizationlib.data.interactor

import cdr.authorizationlib.models.domain.AuthorizationDomain
import cdr.authorizationlib.models.domain.RegistrationDomain

/**
 * Интерактор для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationInteractor {

    /** Авторизация пользователя */
    suspend fun signIn(authorizationDomain: AuthorizationDomain)

    /** Регистрация нового пользователя */
    suspend fun signUp(registrationDomain: RegistrationDomain)
}