package cdr.profilelib.presentation.edit

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme

/**
 * Activity для экрана редактирования профиля
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlAntTheme {
                ProfileEditContent(onFinish = { this.finish() })
            }
        }
    }

    companion object {

        /**
         * Создание [Intent] для запуска [ProfileEditActivity]
         *
         * @param context контекст
         */
        fun newIntent(context: Context): Intent = Intent(context, ProfileEditActivity::class.java)
    }
}