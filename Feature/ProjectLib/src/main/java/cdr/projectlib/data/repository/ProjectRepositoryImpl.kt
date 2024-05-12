package cdr.projectlib.data.repository

import cdr.projectlib.data.converter.toDomain
import cdr.projectlib.data.converter.toRequest
import cdr.projectlib.data.mapper.ProjectMapper
import cdr.projectlib.models.domain.NewProjectDomain
import cdr.projectlib.models.domain.ProjectInfoDomain

/**
 * Реализация [ProjectRepository]
 *
 * @param projectMapper маппер для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectRepositoryImpl(
    private val projectMapper: ProjectMapper
) : ProjectRepository {

    override suspend fun getAllProjects(): List<ProjectInfoDomain> {
        val allProjectsResponse = projectMapper.getAllProjects()
        val allProjectsDomain = allProjectsResponse.map { project -> project.toDomain() }

        return allProjectsDomain
    }

    override suspend fun saveNewProject(newProject: NewProjectDomain) {
        return projectMapper.saveNewProject(newProject.toRequest())
    }
}