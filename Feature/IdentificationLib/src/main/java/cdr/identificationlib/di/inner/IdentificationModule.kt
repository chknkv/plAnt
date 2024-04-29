package cdr.identificationlib.di.inner

import cdr.identificationlib.data.interactor.IdentificationInteractor
import cdr.identificationlib.data.interactor.IdentificationInteractorImpl
import cdr.identificationlib.data.mapper.IdentificationMapper
import cdr.identificationlib.data.mapper.IdentificationMapperImpl
import cdr.identificationlib.data.repository.IdentificationRepository
import cdr.identificationlib.data.repository.IdentificationRepositoryImpl
import cdr.coreutilslib.network.BaseRestClientImpl
import dagger.Module
import dagger.Provides

/**
 * [Module] для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
@Module
internal object IdentificationModule {

    @Provides
    fun provideIdentificationMapper(): IdentificationMapper = IdentificationMapperImpl(
        BaseRestClientImpl().baseRestClient()
    )

    @Provides
    fun provideIdentificationRepository(
        identificationMapper: IdentificationMapper
    ): IdentificationRepository = IdentificationRepositoryImpl(
        identificationMapper = identificationMapper
    )

    @Provides
    fun provideIdentificationInteractor(
        identificationRepository: IdentificationRepository
    ): IdentificationInteractor = IdentificationInteractorImpl(
        identificationRepository = identificationRepository
    )
}