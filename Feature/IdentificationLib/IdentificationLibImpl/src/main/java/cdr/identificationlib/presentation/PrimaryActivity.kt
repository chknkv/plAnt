package cdr.identificationlib.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import cdr.identificationlib.models.Navigator
import cdr.identificationlib.presentation.authorization.AuthorizationFragment
import cdr.identificationlib.presentation.dividing.DividingFragment
import cdr.identificationlib.presentation.registration.RegistrationFragment

/**
 * Activity для модуля авторизации.
 * Данная Activity состоит из нескольких фрагментов (регистрация, авторизация, разводящий)
 *
 * @author Alexandr Chekunkov
 */
internal class PrimaryActivity : FragmentActivity(), Navigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(cdr.coreresourceslib.R.layout.primary_activity)

        supportFragmentManager.commit {
            replace(
                cdr.coreresourceslib.R.id.primary_content_container,
                DividingFragment.newInstance(),
                DividingFragment.TAG
            )
        }
    }

    override fun launchAuthorizationScreen() = addFragment(
        fragment = AuthorizationFragment.newInstance(),
        tag = AuthorizationFragment.TAG
    )

    override fun launchRegistrationScreen() = addFragment(
        fragment = RegistrationFragment.newInstance(),
        tag = RegistrationFragment.TAG
    )

    override fun onNavigationPressed() = supportFragmentManager.popBackStack()

    /** Запуск нового фрагмента */
    private fun addFragment(
        fragment: Fragment,
        tag: String
    ) = supportFragmentManager.commit {
        replace(
            cdr.coreresourceslib.R.id.primary_content_container,
            fragment,
            tag
        ).addToBackStack(null)
    }

    companion object {

        /**
         * Создание [Intent] для запуска [PrimaryActivity]
         */
        fun newIntent(context: Context) = Intent(context, PrimaryActivity::class.java)
    }
}