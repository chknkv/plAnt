package cdr.projectlib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectInfoResponse
import cdr.projectlib.models.data.ProjectStatusResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

/**
 * Реализация [ProjectMapper]
 *
 * @param retrofit клиент для сетевого взаимодействия
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectMapperImpl(
    retrofit: Retrofit
) : ProjectMapper {

    private val client = retrofit.create(ProjectApi::class.java)

//    TODO: приблизительная реализация маппера и api
//    override suspend fun getAllProjects(): List<ProjectInfoResponse> =
//        withContext(Dispatchers.IO) {
//            return@withContext client.getAllProjects()
//        }
//
//    override suspend fun saveNewProject(newProject: NewProjectRequest) =
//        withContext(Dispatchers.IO) {
//            return@withContext client.saveNewProject(newProject)
//        }

    override suspend fun getAllProjects(): List<ProjectInfoResponse> =
        withContext(Dispatchers.IO) {
            delay(500)
            return@withContext allProjects
        }

    override suspend fun saveNewProject(newProject: NewProjectRequest) =
        withContext(Dispatchers.IO) {

            Logger.d("ProjectMapperImpl", "--->>> newProject: $newProject")
            delay(1500)
            throw IllegalArgumentException()
        }

    private val allProjects = listOf(
        ProjectInfoResponse(
            id = 4,
            name = "Месенджер «Телеграмм»",
            author = "@sharikov",
            status = ProjectStatusResponse.OPEN,
            price = 123098.15,
            isHaveExecutor = false,
            description = "Павел Дуров просит протестировать!",
            executor = ""
        ),
        ProjectInfoResponse(
            id = 5,
            name = "Мобильное приложение «Шахматы»",
            author = "@sharikov",
            status = ProjectStatusResponse.OPEN,
            price = 5450.15,
            isHaveExecutor = false,
            description = "",
            executor = ""
        ),
        ProjectInfoResponse(
            id = 6,
            name = "Приложение для ПВЗ «OZON»",
            author = "@sobakov.best",
            status = ProjectStatusResponse.OPEN,
            price = 124444.15,
            isHaveExecutor = false,
            description = "",
            executor = ""
        ),
        ProjectInfoResponse(
            id = 1,
            name = "Мобильное приложение «СБОЛ»",
            author = "@user.test",
            status = ProjectStatusResponse.CLOSED,
            price = 15150.15,
            isHaveExecutor = true,
            executor = "@executor.test",
            description = "Необходимо протестировать несколько экранов"
        ),
        ProjectInfoResponse(
            id = 2,
            name = "Приложение для игры в «Судоку»",
            author = "@user.test",
            status = ProjectStatusResponse.IN_WORK,
            price = 81950.15,
            isHaveExecutor = true,
            executor = "@executor.test",
            description = "Необходимо протестировать все приложение. Профиль, статистика, задачи, уровни, процесс игры и т.д. Оплачу сразу после проведенной работы, в долгу не останусь. Жду обратной связи, спасибо!"
        ),
        ProjectInfoResponse(
            id = 3,
            name = "Мобильный Банк «Тинькофф»",
            author = "@user.test",
            status = ProjectStatusResponse.OPEN,
            price = 1255.15,
            isHaveExecutor = false,
            executor = "@executor.test",
            description = "Необходимо протестировать несколько экранов"
        ),
        ProjectInfoResponse(
            id = 4,
            name = "Социальная сеть «ВКонтакте»",
            author = "@user.test",
            status = ProjectStatusResponse.IN_WORK,
            price = 912450.15,
            isHaveExecutor = true,
            executor = "@executor.test",
            description = "Необходимо протестировать несколько экранов"
        )
    )
}