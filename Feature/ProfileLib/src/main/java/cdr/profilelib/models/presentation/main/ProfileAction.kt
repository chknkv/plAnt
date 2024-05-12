package cdr.profilelib.models.presentation.main

import cdr.profilelib.models.domain.ClientInfoDomain
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
    @JvmInline
    value class LaunchEditProfile(val clientInfo: ClientInfoDomain) : ProfileAction
}