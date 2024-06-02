package cdr.projectlib.presentation.info

import androidx.lifecycle.ViewModel
import cdr.coreutilslib.token.TokenWorker
import cdr.projectlib.models.domain.ClientInfoDomain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * ViewModel для шторки с информацией о приложении
 *
 * @param tokenWorker воркер для работы с токеном
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectInfoViewModel(
    private val tokenWorker: TokenWorker
) : ViewModel() {

    val clientInfo: StateFlow<ClientInfoDomain> get() = _clientInfo.asStateFlow()
    private val _clientInfo = MutableStateFlow(ClientInfoDomain())

    fun fetchInfoAboutClient() {
        val currentUsername = tokenWorker.getUsername()
        val currentRole = tokenWorker.getRole()

        _clientInfo.value = ClientInfoDomain(
            username = currentUsername,
            role = currentRole
        )
    }

    companion object {
        const val ROLE_DEVELOPER = "ROLE_DEVELOPER"
        const val ROLE_QA = "ROLE_QA"
    }
}