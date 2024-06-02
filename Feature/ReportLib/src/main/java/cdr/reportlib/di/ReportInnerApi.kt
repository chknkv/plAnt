package cdr.reportlib.di

import androidx.lifecycle.ViewModelProvider
import javax.inject.Named

/**
 * Внутренее API для модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal interface ReportInnerApi {

    @Named("ReportViewModel")
    fun getReportViewModelFactory(): ViewModelProvider.Factory

    @Named("ReportInfoViewModel")
    fun getReportInfoViewModelFactory(): ViewModelProvider.Factory

    @Named("DetailsReportInfoViewModel")
    fun getDetailsReportInfoViewModelFactory(): ViewModelProvider.Factory
}