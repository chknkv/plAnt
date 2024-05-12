package cdr.projectlib.data.mapper

import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT

/**
 * API-интерфейс для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal interface  ProjectApi {

    /** Получение всех доступных проектов для биржы */
    @GET("/allProjects")
    suspend fun getAllProjects(): List<ProjectInfoResponse>

    /** Создание нового проекта */
    @PUT("/newProject")
    suspend fun saveNewProject(@Body newProject: NewProjectRequest)
}