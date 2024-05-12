package cdr.projectlib.data.mapper

import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectInfoResponse

/**
 * Маппер для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal interface ProjectMapper {

    /** Получение всех доступных проектов для биржы */
    suspend fun getAllProjects(): List<ProjectInfoResponse>

    /** Создание нового проекта */
    suspend fun saveNewProject(newProject: NewProjectRequest)
}