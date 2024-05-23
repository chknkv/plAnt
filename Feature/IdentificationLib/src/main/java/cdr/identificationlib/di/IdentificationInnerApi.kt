package cdr.identificationlib.di

import cdr.identificationlib.data.interactor.IdentificationInteractor

/**
 * Внутренее API для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationInnerApi {

    fun getIdentificationInteractor(): IdentificationInteractor

}