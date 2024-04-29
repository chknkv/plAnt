package cdr.plant.di

import cdr.identificationlib.di.api.IdentificationApi
import cdr.identificationlib.di.api.IdentificationModuleApi
import cdr.identificationlib.launcher.IdentificationLauncher
import cdr.mainscreenlib.di.api.MainScreenApi
import cdr.mainscreenlib.di.api.MainScreenModuleApi
import cdr.mainscreenlib.launcher.MainScreenLauncher
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        IdentificationModuleApi::class,
        MainScreenModuleApi::class
    ]
)
interface AppComponent : IdentificationApi, MainScreenApi {

    override fun getIdentificationLauncher(): IdentificationLauncher

    override fun getMainScreenLauncher(): MainScreenLauncher

}