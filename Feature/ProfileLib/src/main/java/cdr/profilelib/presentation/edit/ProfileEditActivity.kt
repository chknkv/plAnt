package cdr.profilelib.presentation.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.getParcelableObject
import cdr.profilelib.models.domain.ClientInfoDomain

/**
 * Activity для экрана редактирования профиля
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val currentClientInfo = intent.getParcelableObject<ClientInfoDomain>(TAG) ?: error("client info не найден")

        setContent {
            PlAntTheme {
                ProfileEditContent(
                    currentClientInfo = currentClientInfo,
                    onFinish = { this.finish() }
                )
            }
        }
    }

    companion object {

        private const val TAG = "current_client_info"

        /**
         * Создание [Intent] для запуска [ProfileEditActivity]
         *
         * @param context контекст
         */
        fun newIntent(
            context: Context,
            currentClientInfo: ClientInfoDomain
        ): Intent = Intent(context, ProfileEditActivity::class.java).putExtra(TAG, currentClientInfo)
    }
}