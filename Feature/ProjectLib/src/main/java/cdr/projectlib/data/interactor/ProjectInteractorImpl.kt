package cdr.projectlib.data.interactor

import cdr.projectlib.data.repository.ProjectRepository
import cdr.projectlib.models.domain.NewProjectDomain
import cdr.projectlib.models.domain.ProjectInfoDomain

/**
 * Реализация [ProjectInteractor]
 *
 * @param projectRepository репозиторий для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectInteractorImpl(
    private val projectRepository: ProjectRepository
) : ProjectInteractor {

    override suspend fun getAllProjects(): List<ProjectInfoDomain> = projectRepository.getAllProjects()

    override suspend fun saveNewProject(newProject: NewProjectDomain) = projectRepository.saveNewProject(newProject)
}