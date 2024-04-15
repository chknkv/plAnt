package cdr.identificationlib.data.interactor

import cdr.identificationlib.models.domain.AuthorizationDomain
import cdr.identificationlib.models.domain.ClientDomain
import cdr.identificationlib.models.domain.RegistrationDomain

/**
 * Интерактор для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationInteractor {

    /** Авторизация пользователя */
    suspend fun signIn(authorizationDomain: AuthorizationDomain): ClientDomain

    /** Регистрация нового пользователя */
    suspend fun signUp(registrationDomain: RegistrationDomain): ClientDomain
}