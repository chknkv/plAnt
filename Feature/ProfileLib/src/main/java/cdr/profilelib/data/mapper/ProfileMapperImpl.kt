package cdr.profilelib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.profilelib.models.data.ClientInfoResponse
import cdr.profilelib.models.data.ClientInfoRoleResponse
import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest
import cdr.projectlib.models.data.ProjectInfoResponse
import cdr.projectlib.models.data.ProjectStatusResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

/**
 * Реализация [ProfileMapper]
 *
 * @param retrofit клиент для сетевого взаимодействия
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileMapperImpl(
    retrofit: Retrofit
) : ProfileMapper {

    private val client = retrofit.create(ProfileApi::class.java)

//    TODO: приблизительная реализация маппера и api
//    override suspend fun getProfileInfo(): ClientResponse =
//        withContext(Dispatchers.IO) {
//            return@withContext client.getProfileInfo()
//        }
//
//    override suspend fun editClientInfo(newClientInfo: NewClientInfoRequest) =
//        withContext(Dispatchers.IO) {
//            return@withContext client.editClientInfo(newClientInfo)
//        }

    override suspend fun getProfileInfo(): ClientResponse =
        withContext(Dispatchers.IO) {
            delay(500)
            return@withContext clientInfo
        }

    override suspend fun editClientInfo(newClientInfo: NewClientInfoRequest) =
        withContext(Dispatchers.IO) {
            Logger.d("ProfileMapperImpl", "--->>> newClientInfo: $newClientInfo")
            delay(1500)
            throw IllegalArgumentException()
        }

    private val clientInfo = ClientResponse(
        clientInfo = ClientInfoResponse(
            firstName = "Alexandr",
            lastName = "Chekunkov",
            username = "@user.test",
            role = ClientInfoRoleResponse.QA
        ),
        clientProjects = listOf(
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
    )
}