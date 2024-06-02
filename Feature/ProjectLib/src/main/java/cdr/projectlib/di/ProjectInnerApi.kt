package cdr.projectlib.di

import androidx.lifecycle.ViewModelProvider
import javax.inject.Named

/**
 * Внутренее API для модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal interface ProjectInnerApi {

    @Named("MarketViewModel")
    fun getMarketViewModelFactory(): ViewModelProvider.Factory

    @Named("AddProjectViewModel")
    fun getProjectAddViewModelFactory(): ViewModelProvider.Factory

    @Named("ProjectInfoViewModel")
    fun getProjectInfoViewModelFactory(): ViewModelProvider.Factory

}