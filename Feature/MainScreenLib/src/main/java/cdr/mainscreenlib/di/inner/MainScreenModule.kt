package cdr.mainscreenlib.di.inner

import cdr.mainscreenlib.launcher.MainScreenLauncherImpl
import cdr.mainscreenlib.launcher.MainScreenLauncher
import dagger.Module
import dagger.Provides

/**
 * [Module] для функционала модуля главного экрана
 *
 * @author Alexandr Chekunkov
 */
@Module
internal object MainScreenModule {

    @Provides
    fun provideMainScreenLauncher(): MainScreenLauncher = MainScreenLauncherImpl()
}