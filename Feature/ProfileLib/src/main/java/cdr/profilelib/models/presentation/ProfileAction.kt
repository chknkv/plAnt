package cdr.profilelib.models.presentation

import cdr.projectlib.models.domain.ProjectInfoDomain

/**
 * Экшены для экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileAction {

    /** Запуск экрана информации о проекте */
    @JvmInline
    value class LaunchProjectInfoScreen(val projectInfo: ProjectInfoDomain) : ProfileAction

    /** Запуск экрана редактирования профиля клиента */
    object LaunchEditProfile : ProfileAction
}