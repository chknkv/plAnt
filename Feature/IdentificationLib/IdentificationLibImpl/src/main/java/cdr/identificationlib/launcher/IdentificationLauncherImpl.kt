package cdr.identificationlib.launcher

import androidx.activity.ComponentActivity
import cdr.identificationlib.presentation.PrimaryActivity
import cdr.identificationlibimplapi.launcher.IdentificationLaunchData
import cdr.identificationlibimplapi.launcher.IdentificationLauncher

/**
 * Реализация [IdentificationLauncher]
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationLauncherImpl : IdentificationLauncher {

    override fun launchAuthorizationScreen(
        activity: ComponentActivity,
        launchData: IdentificationLaunchData
    ) {
        when (launchData) {
            IdentificationLaunchData.PRIMARY -> activity.startActivity(PrimaryActivity.newIntent(activity))
        }
    }
}