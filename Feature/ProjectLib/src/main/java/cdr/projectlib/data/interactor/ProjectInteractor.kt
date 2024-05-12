package cdr.projectlib.data.interactor

import cdr.projectlib.models.domain.NewProjectDomain
import cdr.projectlib.models.domain.ProjectInfoDomain

/**
 * Интерактор для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal interface ProjectInteractor {

    /** Получение всех доступных проектов для биржы */
    suspend fun getAllProjects(): List<ProjectInfoDomain>

    /** Создание нового проекта */
    suspend fun saveNewProject(newProject: NewProjectDomain)
}