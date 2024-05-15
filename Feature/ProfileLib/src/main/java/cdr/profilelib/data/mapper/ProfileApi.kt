package cdr.profilelib.data.mapper

import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT

/**
 * API-интерфейс для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal interface ProfileApi {

    /** Получение информации о пользователе */
    @GET("/auth/getUserWithProjects")
    suspend fun getProfileInfo(@Header("Authorization") jwtToken: String): ClientResponse

    /** Редактирование информации о пользователе */
    @PUT("/auth/update")
    suspend fun editClientInfo(@Header("Authorization") jwtToken: String, @Body newClientInfo: NewClientInfoRequest)
}