package cdr.profilelib.models.presentation.edit

/**
 * Экшены для экрана регистрации
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileEditAction {

    /** Отображение вспывающего окна с сообщением о незаполненных полях */
    object EmptyFields : ProfileEditAction

    /** Отображение вспывающего окна с сообщением о разных паролях */
    object DifferentPasswords : ProfileEditAction

    /** Отображение вспывающего окна с сообщением о коротком пароле */
    object TinyPassword : ProfileEditAction

    /** Отображение вспывающего окна с сообщением о легком пароле */
    object EasyPassword : ProfileEditAction
}