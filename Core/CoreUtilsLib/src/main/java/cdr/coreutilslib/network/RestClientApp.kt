package cdr.coreutilslib.network

/**
 * Аргумент, который позволяет разделить к какому приложению производить запрос.
 *
 * @author Alexandr Chekunkov
 */
enum class RestClientApp {
    /** Приложение для работы с профилем и авторизацией */
    PROFILE_APP,

    /** Приложение для работы с проектами */
    PROJECT_APP,

    /** Приложение для работы с баг-репортами */
    REPORT_APP
}