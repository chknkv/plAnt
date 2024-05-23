package cdr.identificationlib.launcher

import androidx.activity.ComponentActivity
import cdr.identificationlib.presentation.PrimaryActivity

/**
 * Реализация [IdentificationLauncher]
 *
 * @author Alexandr Chekunkov
 */
class IdentificationLauncherImpl : IdentificationLauncher {

    override fun launchAuthorizationScreen(
        activity: ComponentActivity,
        launchData: IdentificationLaunchData
    ) {
        when (launchData) {
            IdentificationLaunchData.PRIMARY -> activity.startActivity(PrimaryActivity.newIntent(activity))
        }
    }
}