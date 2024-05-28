package cdr.identificationlib.di

import android.content.Context
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.BaseRestClientFactoryImpl
import cdr.coreutilslib.token.TokenWorker
import cdr.coreutilslib.token.TokenWorkerImpl
import cdr.identificationlib.data.interactor.IdentificationInteractor
import cdr.identificationlib.data.interactor.IdentificationInteractorImpl
import cdr.identificationlib.data.mapper.IdentificationMapper
import cdr.identificationlib.data.mapper.IdentificationMapperImpl
import cdr.identificationlib.data.repository.IdentificationRepository
import cdr.identificationlib.data.repository.IdentificationRepositoryImpl
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
    fun provideBaseRestClientFactory(): BaseRestClientFactory = BaseRestClientFactoryImpl()

    @Provides
    fun provideTokenWorker(context: Context): TokenWorker = TokenWorkerImpl(context = context)

    @Provides
    fun provideIdentificationMapper(
        baseRestClientFactory: BaseRestClientFactory
    ): IdentificationMapper = IdentificationMapperImpl(
        restClientFactory = baseRestClientFactory
    )

    @Provides
    fun provideIdentificationRepository(
        identificationMapper: IdentificationMapper,
        tokenWorker: TokenWorker
    ): IdentificationRepository = IdentificationRepositoryImpl(
        identificationMapper = identificationMapper,
        tokenWorker = tokenWorker
    )

    @Provides
    fun provideIdentificationInteractor(
        identificationRepository: IdentificationRepository
    ): IdentificationInteractor = IdentificationInteractorImpl(
        identificationRepository = identificationRepository
    )
}