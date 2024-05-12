package cdr.profilelib.di

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
        fun create(): ProfileComponent
    }
}