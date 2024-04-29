package cdr.plant

import android.os.Bundle
import androidx.activity.ComponentActivity

/**
 * Тестовый экран
 *
 * @author Alexandr Chekunkov
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val identificationLauncher = this.appComponent.getIdentificationLauncher()
//
//        identificationLauncher.lau(
//            activity = this,
//            launchData = IdentificationLaunchData.PRIMARY
//        )

        val mainScreenLauncher = this.appComponent.getMainScreenLauncher()

        mainScreenLauncher.launchMainScreen(this)

        finish()
    }
}
