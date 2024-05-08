package cdr.profilelib.models.presentation

import cdr.projectlib.models.domain.ProjectInfo

/**
 * Экшены для экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileAction {

    /** Запуск экрана информации о проекте */
    @JvmInline
    value class LaunchProjectInfoScreen(val projectInfo: ProjectInfo) : ProfileAction

    /** Запуск экрана редактирования профиля клиента */
    object LaunchEditProfile : ProfileAction
}