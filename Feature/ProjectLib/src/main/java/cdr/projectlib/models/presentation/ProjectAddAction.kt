package cdr.projectlib.models.presentation

/**
 * Экшены для экрана создания нового проекта
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProjectAddAction {

    /** Показать сообщение с пустыми полями */
    object EmptyFields : ProjectAddAction

    /** Показать сообщение с ошибкой о том, что не является ссылкой */
    object NotLink : ProjectAddAction
}