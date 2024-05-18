package cdr.profilelib.data.mapper

import cdr.profilelib.models.data.ClientInfoResponse
import cdr.profilelib.models.data.ClientInfoRoleResponse
import cdr.profilelib.models.data.ClientResponse
import cdr.projectlib.models.data.ProjectApplicationInfoData
import cdr.projectlib.models.data.ProjectInfoResponse
import cdr.projectlib.models.data.ProjectOperationSystemData
import cdr.projectlib.models.data.ProjectStatusResponse
import kotlinx.coroutines.delay

/**
 * Метод [getMockedAProfileInfo] используется для отладки работы профиля при отсутсвии связи с удаленным сервисов.
 *
 * @return мокнутая data-модель с информацией о клиенте и его проектах
 * @author Alexandr Chekunkov
 */
internal suspend fun getMockedAProfileInfo(): ClientResponse {
    delay(1500)
    return MOCKED_PROFILE_INFO
}

private val MOCKED_PROFILE_INFO = ClientResponse(
    clientInfo = ClientInfoResponse(
        username = "cdr.chknkv",
        firstName = "Александр",
        lastName = "Чекунков",
        role = ClientInfoRoleResponse.DEVELOPER
    ),
    clientProjects = listOf(
        ProjectInfoResponse(
            id = 1,
            name = "Мобильное приложение «Судоку»",
            author = "cdr.chknkv",
            status = ProjectStatusResponse.IN_WORK,
            description = "Необходимо протестировать мобильное приложение для игры в головоломку Судоку",
            applicationInfo = ProjectApplicationInfoData(
                operationSystem = ProjectOperationSystemData.ANDROID,
                url = "https://github.com/coder-chekunkov/sudoku"
            ),
            price = 1500.00
        ),
        ProjectInfoResponse(
            id = 1,
            name = "Месенджер «Телеграмм»",
            author = "cdr.chknkv",
            status = ProjectStatusResponse.OPEN,
            description = "Необходимо протестироват работу чата в телеграмме",
            applicationInfo = ProjectApplicationInfoData(
                operationSystem = ProjectOperationSystemData.ANDROID,
                url = "https://github.com/coder-chekunkov/Article-PostgreSQL/blob/main/app/app/src/main/java/com/article/libraryretrofitarticle/spread/Dependencies.kt"
            ),
            price = 11900.00
        )
    )
)