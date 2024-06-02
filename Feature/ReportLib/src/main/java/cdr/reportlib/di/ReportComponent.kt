package cdr.reportlib.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component

/**
 * [Component] для функционала модуля баг-трекеров
 *
 * @author Alexandr Chekunkov
 */
@Component(modules = [ReportModule::class])
internal interface ReportComponent : ReportInnerApi {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ReportComponent
    }
}