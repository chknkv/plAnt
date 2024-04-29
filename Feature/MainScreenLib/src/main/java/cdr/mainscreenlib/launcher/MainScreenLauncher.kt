package cdr.mainscreenlib.launcher

import androidx.activity.ComponentActivity

/**
 * Лаунчер для запуска главного экрана
 *
 * @author Alexandr Chekunkov
 */
interface MainScreenLauncher {

    /**
     * Запуск главного экрана
     *
     * @param activity экран, с которого будет запускаться новый экран
     */
    fun launchMainScreen(activity: ComponentActivity)
}