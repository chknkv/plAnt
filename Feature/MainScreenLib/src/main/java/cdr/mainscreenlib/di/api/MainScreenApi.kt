package cdr.mainscreenlib.di.api

import cdr.mainscreenlib.launcher.MainScreenLauncher

interface MainScreenApi {

    fun getMainScreenLauncher(): MainScreenLauncher
}