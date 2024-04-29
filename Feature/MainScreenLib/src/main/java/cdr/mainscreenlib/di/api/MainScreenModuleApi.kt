package cdr.mainscreenlib.di.api

import cdr.mainscreenlib.launcher.MainScreenLauncher
import cdr.mainscreenlib.launcher.MainScreenLauncherImpl
import dagger.Module
import dagger.Provides

@Module
object MainScreenModuleApi {

    @Provides
    fun provideMainScreenLauncher(): MainScreenLauncher = MainScreenLauncherImpl()

}