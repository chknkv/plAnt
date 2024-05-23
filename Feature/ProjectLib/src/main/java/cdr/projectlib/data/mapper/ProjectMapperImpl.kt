package cdr.projectlib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.RestClientApp
import cdr.coreutilslib.token.TokenWorker
import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Реализация [ProjectMapper]
 *
 * @param restClientFactory фабрика для создания клиента сетевого взаимодействия
 * @param tokenWorker воркер для работы с токеном
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectMapperImpl(
    private val restClientFactory: BaseRestClientFactory,
    private val tokenWorker: TokenWorker
) : ProjectMapper {

    private val token = tokenWorker.getToken()
    private val client = restClientFactory
        .baseRestClient(RestClientApp.PROJECT_APP)
        .create(ProjectApi::class.java)

    override suspend fun getAllProjects(): List<ProjectInfoResponse> =
        withContext(Dispatchers.IO) {
            val allProjects = client.getAllProjects(token)
//            val allProjects = getMockedAllProjects()
            Logger.i(TAG, "[allProjectsResponse] bodyResponse: $allProjects")

            return@withContext allProjects
        }

    override suspend fun saveNewProject(newProject: NewProjectRequest) =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[saveNewProject] bodyRequest: $newProject")

            client.saveNewProject(token, newProject)
        }

    companion object {
        private const val TAG = "ProjectMapper"
    }
}