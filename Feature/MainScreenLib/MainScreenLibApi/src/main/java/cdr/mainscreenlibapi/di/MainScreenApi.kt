package cdr.mainscreenlibapi.di

import cdr.mainscreenlibapi.launcher.MainScreenLauncher

/**
 * API функционала модуля главного экрана
 *
 * @author Alexandr Chekunkov
 */
interface MainScreenApi {

    fun getMainScreenLauncher(): MainScreenLauncher
}