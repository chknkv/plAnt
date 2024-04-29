package cdr.plant

import android.app.Application
import android.content.Context
import cdr.plant.di.AppComponent
import cdr.plant.di.DaggerAppComponent

/**
 *
 * @author Alexandr Chekunkov
 */
class MainApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is MainApplication -> appComponent
        else -> applicationContext.appComponent
    }