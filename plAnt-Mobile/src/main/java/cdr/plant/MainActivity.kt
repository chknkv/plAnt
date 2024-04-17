package cdr.plant

import android.os.Bundle
import androidx.activity.ComponentActivity
import cdr.identificationlib.launcher.IdentificationLaunchData
import cdr.identificationlib.launcher.IdentificationLauncherImpl

/**
 * Тестовый экран
 *
 * @author Alexandr Chekunkov
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        IdentificationLauncherImpl().launchAuthorizationScreen(
            activity = this,
            launchData = IdentificationLaunchData.PRIMARY
        )
        finish()
    }
}
