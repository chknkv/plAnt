package cdr.mainscreenlib.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import cdr.mainscreenlib.presentation.profile.ProfileFragment

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

        supportFragmentManager.commit {
            replace(
                cdr.coreresourceslib.R.id.primary_content_container,
                ProfileFragment.newInstance(),
                ProfileFragment.TAG
            )
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