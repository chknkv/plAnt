package cdr.identificationlib.data.mapper

import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.RegistrationRequest
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

    override suspend fun signIn(authorizationRequest: AuthorizationRequest): ClientResponse =
        withContext(Dispatchers.IO) {
            return@withContext client.signIn(authorizationRequest)
        }

    override suspend fun signUp(registrationRequest: RegistrationRequest): ClientResponse =
        withContext(Dispatchers.IO) {
            return@withContext client.signUp(registrationRequest)
        }
}