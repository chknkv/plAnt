package cdr.identificationlib.di.inner

import dagger.Component

/**
 * [Component] для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
@Component(
    modules = [IdentificationModule::class]
)
internal interface IdentificationComponent : IdentificationInnerApi {

    @Component.Factory
    interface Factory {
        fun create(): IdentificationComponent
    }
}