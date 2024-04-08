package cdr.authorizationlib.data.mapper

import cdr.authorizationlib.models.data.AuthorizationRequest
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * API-интерфейс для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationApi {

    @POST("login")
    suspend fun signIn(@Body authorizationRequest: AuthorizationRequest)
}