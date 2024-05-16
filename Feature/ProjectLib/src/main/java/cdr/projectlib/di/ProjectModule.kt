package cdr.projectlib.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.BaseRestClientFactoryImpl
import cdr.coreutilslib.token.TokenWorker
import cdr.coreutilslib.token.TokenWorkerImpl
import cdr.projectlib.data.interactor.ProjectInteractor
import cdr.projectlib.data.interactor.ProjectInteractorImpl
import cdr.projectlib.data.mapper.ProjectMapper
import cdr.projectlib.data.mapper.ProjectMapperImpl
import cdr.projectlib.data.repository.ProjectRepository
import cdr.projectlib.data.repository.ProjectRepositoryImpl
import cdr.projectlib.presentation.add.ProjectAddViewModel
import cdr.projectlib.presentation.market.MarketViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * [Module] для функционала модуля проекта
 *
 * @author Alexandr Chekunkov
 */
@Module
internal object ProjectModule {

    @Provides
    fun provideBaseRestClientFactory(): BaseRestClientFactory = BaseRestClientFactoryImpl()

    @Provides
    fun provideTokenWorker(): TokenWorker = TokenWorkerImpl()

    @Provides
    fun provideProjectMapper(
        baseRestClientFactory: BaseRestClientFactory,
        tokenWorker: TokenWorker
    ): ProjectMapper = ProjectMapperImpl(
        restClientFactory = baseRestClientFactory,
        tokenWorker = tokenWorker
    )

    @Provides
    fun provideProjectRepository(projectMapper: ProjectMapper): ProjectRepository =
        ProjectRepositoryImpl(projectMapper)

    @Provides
    fun provideProjectInteractor(projectRepository: ProjectRepository): ProjectInteractor =
        ProjectInteractorImpl(projectRepository)

    @Provides
    @Named("MarketViewModel")
    fun provideMarketViewModelFactory(projectInteractor: ProjectInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return MarketViewModel(projectInteractor) as T
            }
        }

    @Provides
    @Named("AddProjectViewModel")
    fun provideAddProjectViewModelFactory(projectInteractor: ProjectInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return ProjectAddViewModel(projectInteractor) as T
            }
        }
}