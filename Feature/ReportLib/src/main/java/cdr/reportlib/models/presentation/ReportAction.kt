package cdr.reportlib.models.presentation

/**
 * Экшены для экрана создания баг-репорта
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ReportAction {

    /** Отображение вспывающего окна с сообщением о незаполненных полях */
    object EmptyFields : ReportAction
}