package cdr.identificationlib.data.mapper

import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.RegistrationRequest
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.PUT

/**
 * API-интерфейс для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationApi {

    @POST("/login")
    suspend fun signIn(@Body authorizationRequest: AuthorizationRequest): ClientResponse

    @PUT("/registration")
    suspend fun signUp(@Body registrationRequest: RegistrationRequest) : ClientResponse
}