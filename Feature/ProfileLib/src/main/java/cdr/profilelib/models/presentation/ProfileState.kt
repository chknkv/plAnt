package cdr.profilelib.models.presentation

import cdr.profilelib.models.domain.ClientInfo
import cdr.projectlib.models.domain.ProjectInfo

/**
 * Состояние экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileState {

    /** Загрузка */
    object Loading : ProfileState

    /** Ошибка */
    object Error : ProfileState

    /**
     * Успех
     *
     * @param data UI-модель, содержащая в себе данные о профиле
     */
    @JvmInline
    value class Successful(val data: ProfileInfo = ProfileInfo()) : ProfileState
}

/**
 * UI-модель, содержащая в себе данные о профиле клиента
 *
 * @param clientInfo UI-модель с информацией о клиента
 * @param projectInfoList список UI-моделей с информацией о проекте клиента
 *
 * @author Alexandr Chekunkov
 */
internal data class ProfileInfo(
    val clientInfo: ClientInfo = ClientInfo(),
    val projectInfoList: List<ProjectInfo> = emptyList()
)