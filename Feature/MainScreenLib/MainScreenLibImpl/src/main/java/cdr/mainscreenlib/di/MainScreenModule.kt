package cdr.mainscreenlib.di

import cdr.mainscreenlib.launcher.MainScreenLauncherImpl
import cdr.mainscreenlib.presentation.chat.ChatViewModel
import cdr.mainscreenlibapi.launcher.MainScreenLauncher
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
    fun provideChatViewModel(): ChatViewModel = ChatViewModel()

    @Provides
    fun provideMainScreenLauncher(): MainScreenLauncher = MainScreenLauncherImpl()
}