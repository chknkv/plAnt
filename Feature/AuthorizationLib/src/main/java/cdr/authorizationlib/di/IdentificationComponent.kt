package cdr.authorizationlib.di

import cdr.authorizationlib.data.interactor.IdentificationInteractor
import dagger.Component

/**
 * [Component] для функционала авторизационного модуля
 *
 * @author Alexandr Chekunkov
 */
@Component(modules = [IdentificationModule::class])
internal interface IdentificationComponent {

    val identificationInteractor: IdentificationInteractor

}