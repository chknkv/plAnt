package cdr.projectlib.di

import android.content.Context
import dagger.BindsInstance
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
        fun create(@BindsInstance context: Context): ProjectComponent
    }
}