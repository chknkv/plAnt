package cdr.projectlib.data.mapper

import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

/**
 * API-интерфейс для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal interface  ProjectApi {

    /** Получение всех доступных проектов для биржы */
    @GET("/projects/findAll")
    suspend fun getAllProjects(@Header("Authorization") jwtToken: String): List<ProjectInfoResponse>

    /** Создание нового проекта */
    @POST("/projects/create")
    suspend fun saveNewProject(@Header("Authorization") jwtToken: String, @Body newProject: NewProjectRequest)
}