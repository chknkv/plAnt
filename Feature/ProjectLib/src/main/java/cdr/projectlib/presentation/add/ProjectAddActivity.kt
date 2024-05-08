package cdr.projectlib.presentation.add

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme

/**
 * Активити для экрана создания нового профиля
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectAddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlAntTheme {
                ProjectAddContent(onFinish = { this.finish() })
            }
        }
    }

    companion object {

        /**
         * Создание [Intent] для запуска [ProjectAddActivity]
         *
         * @param context контекст
         */
        fun newIntent(context: Context): Intent = Intent(context, ProjectAddActivity::class.java)
    }
}