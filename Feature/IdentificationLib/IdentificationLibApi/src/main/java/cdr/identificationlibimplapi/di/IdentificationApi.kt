package cdr.identificationlibimplapi.di

import cdr.identificationlibimplapi.launcher.IdentificationLauncher

/**
 * API функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
interface IdentificationApi {

    fun getIdentificationLauncher(): IdentificationLauncher
}
