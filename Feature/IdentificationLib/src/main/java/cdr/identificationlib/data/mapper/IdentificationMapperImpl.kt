package cdr.identificationlib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.RestClientApp
import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.RegistrationRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Реализация [IdentificationMapper]
 *
 * @param restClientFactory фабрика для создания клиента сетевого взаимодействия
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationMapperImpl(
    private val restClientFactory: BaseRestClientFactory
) : IdentificationMapper {

    private val client = restClientFactory
        .baseRestClient(RestClientApp.PROFILE_APP)
        .create(IdentificationApi::class.java)

    override suspend fun signIn(authorizationRequest: AuthorizationRequest): ClientResponse =
        withContext(Dispatchers.IO) {
            val newClient = client.signIn(authorizationRequest)
            Logger.i(TAG, "[signIn] bodyRequest: $authorizationRequest")
            Logger.i(TAG, "[signIn] bodyResponse: $newClient")

            return@withContext newClient
        }

    override suspend fun signUp(registrationRequest: RegistrationRequest): ClientResponse =
        withContext(Dispatchers.IO) {
            val newClient = client.signUp(registrationRequest)
            Logger.i(TAG, "[signUp] bodyRequest: $registrationRequest")
            Logger.i(TAG, "[signUp] bodyResponse: $newClient")

            return@withContext newClient
        }

    companion object {
        private const val TAG = "IdentificationMapper"
    }
}