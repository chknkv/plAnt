package cdr.mainscreenlib.launcher

import androidx.activity.ComponentActivity
import cdr.mainscreenlib.presentation.PrimaryActivity
import cdr.mainscreenlibapi.launcher.MainScreenLauncher


/**
 * Реализация [MainScreenLauncher]
 *
 * @author Alexandr Chekunkov
 */
internal class MainScreenLauncherImpl : MainScreenLauncher {


    override fun launchMainScreen(activity: ComponentActivity) {
        activity.startActivity(PrimaryActivity.newIntent(activity))
    }
}