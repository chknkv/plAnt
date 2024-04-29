package cdr.identificationlib.launcher

import androidx.activity.ComponentActivity

/**
 * Лаунчер для запуска экранов в модуле авторизации
 *
 * @author Alexandr Chekunkov
 */
interface IdentificationLauncher {

    /**
     * Запуск нужного экрана
     *
     * @param activity экран, на котором запускается новый экран
     * @param launchData нужная точка входа
     */
    fun launchAuthorizationScreen(
        activity: ComponentActivity,
        launchData: IdentificationLaunchData
    )

}