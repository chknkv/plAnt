package cdr.projectlib.data.mapper

import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectInfoResponse
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Реализация [ProjectMapper]
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectMapperImpl : ProjectMapper {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .build()
    private val client = retrofit.create(ProjectApi::class.java)

    override suspend fun getAllProjects(): List<ProjectInfoResponse> =
        withContext(Dispatchers.IO) {
            return@withContext client.getAllProjects(MOCKED_JWT_TOKEN)
        }

    override suspend fun saveNewProject(newProject: NewProjectRequest) =
        withContext(Dispatchers.IO) {
            return@withContext client.saveNewProject(MOCKED_JWT_TOKEN, newProject)
        }

    companion object {
        private const val BASE_URL = "http://172.20.10.6:8081"

        private const val MOCKED_JWT_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJ1c2VybmFtZSI6InV0dHN2bGFkX25ldyIsImlhdCI6MTcxNTYxODk2NiwiaXNzIjoicGxBbnQiLCJleHAiOjE3MTU3MDUzNjZ9.1_hDMDf9FjtXdIO6DvcvnSVfh0a0uCLnJAWL-JzJdas"
    }
}