package cdr.identificationlib.di.api

import cdr.identificationlib.launcher.IdentificationLauncher

interface IdentificationApi {

    fun getIdentificationLauncher(): IdentificationLauncher

}