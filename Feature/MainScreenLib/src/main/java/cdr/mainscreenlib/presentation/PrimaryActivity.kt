package cdr.mainscreenlib.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import cdr.coreutilslib.utils.getSerializableObject
import cdr.identificationlib.models.domain.ClientDomain

/**
 * Activity для модуля главного экрана.
 * Данная Activity состоит из нескольких фрагментов (биржа, чат, профиль).
 *
 * @author Alexandr Chekunkov
 */
internal class PrimaryActivity : FragmentActivity() {

    private lateinit var clientDomain: ClientDomain

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(cdr.coreresourceslib.R.layout.primary_activity)

        clientDomain = intent.getSerializableObject(EXTRA_CLIENT_DOMAIN) ?: error("clientDomain не найден")

    }

    companion object {

        /** Данные о авторизированном клиенте */
        private const val EXTRA_CLIENT_DOMAIN = "client_data"

        /**
         * Создание [Intent] для запуска [PrimaryActivity]
         *
         * @param context контекст
         * @param clientData модель, описывающая сущность клиента после авторизации или регистрации
         */
        fun newIntent(context: Context, clientData: ClientDomain): Intent =
            Intent(context, PrimaryActivity::class.java).putExtra(EXTRA_CLIENT_DOMAIN, clientData)
    }
}