package cdr.authorizationlib.data.repository

import cdr.authorizationlib.models.domain.Authorization

/**
 * Репозиторий для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationRepository {

    /** Авторизация в системе */
    suspend fun signIn(authorizationData: Authorization)

}