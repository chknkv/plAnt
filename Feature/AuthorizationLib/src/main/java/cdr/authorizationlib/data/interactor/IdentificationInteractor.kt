package cdr.authorizationlib.data.interactor

import cdr.authorizationlib.models.domain.Authorization

/**
 * Интерактор для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationInteractor {

    /** Авторизация пользователя */
    suspend fun signIn(authorizationData: Authorization)

    /** Регистрация нового пользователя */
    suspend fun signUp()
}