package cdr.profilelib.models.presentation.main

import cdr.profilelib.models.domain.ClientInfoDomain
import cdr.projectlib.models.domain.ProjectInfoDomain

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
    val clientInfo: ClientInfoDomain = ClientInfoDomain(),
    val projectInfoList: List<ProjectInfoDomain> = emptyList()
)