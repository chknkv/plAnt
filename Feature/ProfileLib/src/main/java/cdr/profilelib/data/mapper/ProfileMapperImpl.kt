package cdr.profilelib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

/**
 * Реализация [ProfileMapper]
 *
 * @param retrofit клиент для сетевого взаимодействия
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileMapperImpl(
    retrofit: Retrofit
) : ProfileMapper {

    private val client = retrofit.create(ProfileApi::class.java)

    //    TODO: приблизительная реализация маппера и api
    override suspend fun getProfileInfo(): ClientResponse =
        withContext(Dispatchers.IO) {
            return@withContext client.getProfileInfo()
        }
//
//    override suspend fun editClientInfo(newClientInfo: NewClientInfoRequest) =
//        withContext(Dispatchers.IO) {
//            return@withContext client.editClientInfo(newClientInfo)
//        }

    override suspend fun editClientInfo(newClientInfo: NewClientInfoRequest) =
        withContext(Dispatchers.IO) {
            Logger.d("ProfileMapperImpl", "--->>> newClientInfo: $newClientInfo")
            delay(1500)
            throw IllegalArgumentException()
        }

}