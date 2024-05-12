package cdr.profilelib.data.mapper

import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

/**
 * API-интерфейс для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal interface ProfileApi {

    /** Получение информации о пользователе */
    @GET("/clientInfo")
    suspend fun getProfileInfo(): ClientResponse

    /** Редактирование информации о пользователе */
    @PUT("/editClient")
    suspend fun editClientInfo(@Body newClientInfo: NewClientInfoRequest)
}