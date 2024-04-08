package cdr.authorizationlib.di

import cdr.authorizationlib.presentation.dividing.DividingViewModel
import dagger.Module
import dagger.Provides

/**
 * [Module] для функционала авторизационного модуля
 *
 * @author Alexandr Chekunkov
 */
@Module
internal object AuthorizationModule {

    @Provides
    fun provideDividingViewModel(): DividingViewModel = DividingViewModel()

}