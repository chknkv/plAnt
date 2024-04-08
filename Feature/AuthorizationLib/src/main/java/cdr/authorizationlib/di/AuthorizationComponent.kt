package cdr.authorizationlib.di

import cdr.authorizationlib.presentation.dividing.DividingViewModel
import dagger.Component

/**
 * [Component] для функционала авторизационного модуля
 *
 * @author Alexandr Chekunkov
 */
@Component(
    modules = [AuthorizationModule::class]
)
internal interface AuthorizationComponent {

    @Component.Builder
    interface Builder {

        fun build(): AuthorizationComponent
    }

    val dividingViewModel: DividingViewModel
}