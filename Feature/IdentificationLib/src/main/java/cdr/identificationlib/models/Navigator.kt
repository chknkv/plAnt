package cdr.identificationlib.models

/**
 * Навигатор для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface Navigator {

    /** Запуск экрана авторизации */
    fun launchAuthorizationScreen()

    /** Запуск экрана регистрации */
    fun launchRegistrationScreen()

    /** Нажатие на кнопку навигации */
    fun onNavigationPressed()
}