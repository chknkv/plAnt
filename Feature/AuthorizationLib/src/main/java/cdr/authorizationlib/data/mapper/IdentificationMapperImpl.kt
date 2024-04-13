package cdr.authorizationlib.data.mapper

import cdr.authorizationlib.models.data.AuthorizationRequest
import cdr.authorizationlib.models.data.RegistrationRequest
import cdr.coreutilslib.logs.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit

/**
 * Реализация [IdentificationMapper]
 *
 * @param retrofit базовый клиент для сетевого взаимодействия
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationMapperImpl(
    retrofit: Retrofit
) : IdentificationMapper {

    private val client = retrofit.create(IdentificationApi::class.java)

    override suspend fun signIn(authorizationRequest: AuthorizationRequest) {
        withContext(Dispatchers.IO) {
            val client = client.signIn(authorizationRequest)

            Logger.d("IdentificationMapperImpl", "--->>> client: $client")
        }
    }

    override suspend fun signUp(registrationRequest: RegistrationRequest) {
        withContext(Dispatchers.IO) {
            val client = client.signUp(registrationRequest)

            Logger.d("IdentificationMapperImpl", "--->>> client: $client")
        }
    }

}