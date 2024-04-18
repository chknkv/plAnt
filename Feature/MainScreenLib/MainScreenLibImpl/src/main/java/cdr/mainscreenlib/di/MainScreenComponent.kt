package cdr.mainscreenlib.di

import dagger.Component

/**
 * [Component] для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
@Component(modules = [MainScreenModule::class])
internal interface MainScreenComponent : MainScreenInnerApi {

    @Component.Factory
    interface Factory {
        fun create(): MainScreenComponent
    }
}