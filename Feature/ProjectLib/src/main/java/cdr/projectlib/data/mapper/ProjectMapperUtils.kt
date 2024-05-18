package cdr.projectlib.data.mapper

import cdr.projectlib.models.data.ProjectApplicationInfoData
import cdr.projectlib.models.data.ProjectInfoResponse
import cdr.projectlib.models.data.ProjectOperationSystemData
import cdr.projectlib.models.data.ProjectStatusResponse
import kotlinx.coroutines.delay

/**
 * Метод [getMockedAllProjects] используется для отладки работы биржы при отсутсвии связи с удаленным сервисов.
 *
 * @return список моккнутых data-модель с информацией о проекте
 * @author Alexandr Chekunkov
 */
internal suspend fun getMockedAllProjects(): List<ProjectInfoResponse> {
    delay(1500)
    return MOCKED_ALL_PROJECTS
}

private val MOCKED_ALL_PROJECTS = listOf(
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
    ),
    ProjectInfoResponse(
        id = 1,
        name = "Социальная сеть «ВКонтакте»",
        author = "uttss.vlad",
        status = ProjectStatusResponse.CLOSED,
        description = "Много багов по всему приложению. Необходимо найти и устранить все",
        applicationInfo = ProjectApplicationInfoData(
            operationSystem = ProjectOperationSystemData.ANDROID,
            url = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim. Donec pede justo, fringilla vel, aliquet nec, vulputate eget, arcu. In enim justo, rhoncus ut, imperdiet a, venenatis vitae, justo. Nullam dictum felis eu pede mollis pretium. Integer tincidunt. Cras dapibus. Vivamus elementum semper nisi. Aenean vulputate eleifend tellus. Aenean leo ligula, porttitor eu, consequat vitae, eleifend ac, enim. Aliquam lorem ante, dapibus in, viverra quis, feugiat a, tellus. Phasellus viverra nulla ut metus varius laoreet. Quisque rutrum. Aenean imperdiet. Etiam ultricies nisi vel augue. Curabitur ullamcorper ultricies nisi. Nam eget dui. Etiam rhoncus. Maecenas tempus, tellus eget condimentum rhoncus, sem quam semper libero, sit amet adipiscing sem neque sed ipsum. Nam quam nunc, blandit vel, luctus pulvinar, hendrerit id, lorem. Maecenas nec odio et ante tincidunt tempus. Donec vitae sapien ut libero venenatis faucibus. Nullam quis ante. Etiam sit amet orci eget eros faucibus tincidunt. Duis leo. Sed fringilla mauris sit amet nibh. Donec sodales sagittis magna. Sed consequat, leo eget bibendum sodales, augue velit cursus nunc,"
        ),
        price = 11900.00
    ),
    ProjectInfoResponse(
        id = 1,
        name = "Месенджер «Whats'up»",
        author = "utts.vlad",
        status = ProjectStatusResponse.UNKNOWN,
        description = "Необходимо протестироват работу профиля в приложении",
        applicationInfo = ProjectApplicationInfoData(
            operationSystem = ProjectOperationSystemData.ANDROID,
            url = "https://www.blindtextgenerator.com/lorem-ipsum"
        ),
        price = 11900.00
    )
)