package cdr.mainscreenlib.models.presentation

/**
 * Экшены для экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileAction {

    /** Запуск экрана информации о проекте */
    @JvmInline
    value class LaunchProjectInfoScreen(val projectId: Int) : ProfileAction

    /** Запуск экрана редактирования профиля клиента */
    object LaunchEditProfile : ProfileAction
}