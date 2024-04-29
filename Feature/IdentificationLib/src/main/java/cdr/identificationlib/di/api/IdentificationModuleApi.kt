package cdr.identificationlib.di.api

import cdr.identificationlib.launcher.IdentificationLauncher
import cdr.identificationlib.launcher.IdentificationLauncherImpl
import dagger.Module
import dagger.Provides

@Module
object IdentificationModuleApi {

    @Provides
    fun provideIdentificationLauncher(): IdentificationLauncher = IdentificationLauncherImpl()
}