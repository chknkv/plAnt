package cdr.mainscreenlib.launcher

import androidx.activity.ComponentActivity
import cdr.identificationlib.models.domain.ClientDomain
import cdr.mainscreenlib.presentation.PrimaryActivity

/**
 * Реализация [MainScreenLauncher]
 *
 * @author Alexandr Chekunkov
 */
class MainScreenLauncherImpl : MainScreenLauncher {

    override fun launchMainScreen(activity: ComponentActivity, clientData: ClientDomain) {
        activity.startActivity(PrimaryActivity.newIntent(activity, clientData))
    }
}