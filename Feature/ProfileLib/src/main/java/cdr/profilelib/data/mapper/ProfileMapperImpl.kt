package cdr.profilelib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.RestClientApp
import cdr.coreutilslib.token.TokenWorker
import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Реализация [ProfileMapper]
 *
 * @param restClientFactory фабрика для создания клиента сетевого взаимодействия
 * @param tokenWorker воркер для работы с токеном
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileMapperImpl(
    private val restClientFactory: BaseRestClientFactory,
    private val tokenWorker: TokenWorker
) : ProfileMapper {

    private val token = tokenWorker.getToken()
    private val client = restClientFactory
        .baseRestClient(RestClientApp.PROFILE_APP)
        .create(ProfileApi::class.java)

    override suspend fun getProfileInfo(): ClientResponse =
        withContext(Dispatchers.IO) {
//            val profileInfo = client.getProfileInfo(token)
            val profileInfo = getMockedAProfileInfo()
            Logger.i(TAG, "[getProfileInfo] bodyResponse: $profileInfo")

            return@withContext profileInfo
        }

    override suspend fun editClientInfo(newClientInfo: NewClientInfoRequest) =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[editClientInfo] bodyRequest: $newClientInfo")

            client.editClientInfo(token, newClientInfo)
        }

    companion object {
        private const val TAG = "ProfileMapper"
    }
}