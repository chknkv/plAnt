package cdr.mainscreenlib.launcher

import androidx.activity.ComponentActivity
import cdr.identificationlib.models.domain.ClientDomain

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
     * @param clientData модель, описывающая сущность клиента после авторизации или регистрации
     */
    fun launchMainScreen(activity: ComponentActivity, clientData: ClientDomain)
}