package cdr.profilelib.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

/**
 * [Component] для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
@Component(modules = [ProfileModule::class])
internal interface ProfileComponent : ProfileInnerApi {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ProfileComponent
    }
}