package cdr.identificationlib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.identificationlib.models.data.AuthorizationRequest
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

    override suspend fun signIn(authorizationRequest: AuthorizationRequest) {
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[signIn] - request --->>> $authorizationRequest")

            val client = client.signIn(authorizationRequest)

            Logger.i(TAG, "[signIn] - response --->>> $client")
        }
    }

    override suspend fun signUp(registrationRequest: RegistrationRequest) {
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[signUp] - request --->>> $registrationRequest")

            val client = client.signUp(registrationRequest)

            Logger.i(TAG, "[signUp] - response --->>> $client")
        }
    }

    companion object {
        private const val TAG = "IdentificationMapperImpl"
    }
}