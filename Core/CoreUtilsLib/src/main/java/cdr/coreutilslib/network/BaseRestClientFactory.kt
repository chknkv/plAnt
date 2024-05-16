package cdr.coreutilslib.network

import retrofit2.Retrofit

/**
 * Базовый клиент для сетевого взаимодействия
 *
 * @author Alexandr Chekunkov
 */
interface BaseRestClientFactory {

    fun baseRestClient(app: RestClientApp): Retrofit
}