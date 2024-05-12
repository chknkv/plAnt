package cdr.projectlib.di

import dagger.Component

/**
 * [Component] для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
@Component(modules = [ProjectModule::class])
internal interface ProjectComponent : ProjectInnerApi {

    @Component.Factory
    interface Factory {
        fun create(): ProjectComponent
    }
}