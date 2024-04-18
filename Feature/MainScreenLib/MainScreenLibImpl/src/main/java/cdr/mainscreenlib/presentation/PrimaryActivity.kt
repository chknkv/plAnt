package cdr.mainscreenlib.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity

/**
 * Activity для модуля главного экрана.
 * Данная Activity состоит из нескольких фрагментов (биржа, чат, профиль).
 *
 * @author Alexandr Chekunkov
 */
internal class PrimaryActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(cdr.coreresourceslib.R.layout.primary_activity)

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