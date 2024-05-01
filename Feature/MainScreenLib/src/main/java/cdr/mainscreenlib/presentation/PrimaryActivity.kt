package cdr.mainscreenlib.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme

/**
 * Activity для модуля главного экрана.
 * Данная Activity состоит из нескольких фрагментов (биржа, чат, профиль).
 *
 * @author Alexandr Chekunkov
 */
internal class PrimaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlAntTheme {
                PrimaryContent()
            }
        }
    }

    companion object {

        /**
         * Создание [Intent] для запуска [PrimaryActivity]
         *
         * @param context контекст
         */
        fun newIntent(context: Context): Intent = Intent(context, PrimaryActivity::class.java)
    }
}