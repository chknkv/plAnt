package cdr.reportlib.di

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.BaseRestClientFactoryImpl
import cdr.coreutilslib.token.TokenWorker
import cdr.coreutilslib.token.TokenWorkerImpl
import cdr.reportlib.data.interactor.ReportInteractor
import cdr.reportlib.data.interactor.ReportInteractorImpl
import cdr.reportlib.data.mapper.ReportMapper
import cdr.reportlib.data.mapper.ReportMapperImpl
import cdr.reportlib.data.repository.ReportRepository
import cdr.reportlib.data.repository.ReportRepositoryImpl
import cdr.reportlib.presentation.info.ReportInfoViewModel
import cdr.reportlib.presentation.info.details.DetailsReportInfoViewModel
import cdr.reportlib.presentation.report.ReportViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * [Module] для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
@Module
internal object ReportModule {

    @Provides
    fun provideBaseRestClientFactory(): BaseRestClientFactory = BaseRestClientFactoryImpl()

    @Provides
    fun provideTokenWorker(context: Context): TokenWorker = TokenWorkerImpl(context = context)

    @Provides
    fun provideReportMapper(
        baseRestClientFactory: BaseRestClientFactory,
        tokenWorker: TokenWorker
    ): ReportMapper = ReportMapperImpl(
        restClientFactory = baseRestClientFactory,
        tokenWorker = tokenWorker
    )

    @Provides
    fun provideReportRepository(reportMapper: ReportMapper): ReportRepository =
        ReportRepositoryImpl(reportMapper = reportMapper)

    @Provides
    fun provideReportInteractor(reportRepository: ReportRepository): ReportInteractor =
        ReportInteractorImpl(reportRepository = reportRepository)

    @Provides
    @Named("ReportViewModel")
    fun provideReportViewModelFactory(reportInteractor: ReportInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
                ReportViewModel(reportInteractor) as T
        }

    @Provides
    @Named("ReportInfoViewModel")
    fun provideReportInfoViewModelFactory(reportInteractor: ReportInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
                ReportInfoViewModel(reportInteractor) as T
        }

    @Provides
    @Named("DetailsReportInfoViewModel")
    fun provideDetailsReportInfoViewModelFactory(reportInteractor: ReportInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T =
                DetailsReportInfoViewModel(reportInteractor) as T
        }
}