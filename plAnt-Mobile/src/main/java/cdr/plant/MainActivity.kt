package cdr.plant

import android.os.Bundle
import androidx.activity.ComponentActivity
import cdr.identificationlib.launcher.AuthorizationLaunchData
import cdr.identificationlib.launcher.AuthorizationLauncherImpl

/**
 * Тестовый экран
 *
 * @author Alexandr Chekunkov
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthorizationLauncherImpl().launchAuthorizationScreen(
            activity = this,
            launchData = AuthorizationLaunchData.PRIMARY
        )
        finish()
    }
}
