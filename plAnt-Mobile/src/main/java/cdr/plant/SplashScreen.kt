package cdr.plant

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cdr.corecompose.theming.PlAntTheme
import cdr.identificationlib.launcher.IdentificationLaunchData
import cdr.identificationlib.launcher.IdentificationLauncherImpl

/**
 * Кастомный Splash Screen
 *
 * @author Alexandr Chekunkov
 */
@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlAntTheme {
                SplashScreenContent(launchNextScreen = { launchNextScreen() })
            }
        }
    }

    private fun launchNextScreen() {
        IdentificationLauncherImpl().launchAuthorizationScreen(this, IdentificationLaunchData.PRIMARY)
        finish()
    }
}