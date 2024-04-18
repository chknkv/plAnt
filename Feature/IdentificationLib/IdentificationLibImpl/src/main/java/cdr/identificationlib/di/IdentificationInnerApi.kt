package cdr.identificationlib.di

import cdr.identificationlib.data.interactor.IdentificationInteractor
import cdr.identificationlibimplapi.di.IdentificationApi

/**
 * Внутренее API для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface IdentificationInnerApi : IdentificationApi {

    fun getIdentificationInteractor(): IdentificationInteractor

}