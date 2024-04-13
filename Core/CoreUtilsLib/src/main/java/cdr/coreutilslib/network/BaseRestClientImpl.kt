package cdr.coreutilslib.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Реализация [BaseRestClient]
 *
 * @author Alexandr Chekunkov
 */
class BaseRestClientImpl : BaseRestClient {

    override fun baseRestClient(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()

    companion object {
        private const val BASE_URL = "http://172.20.10.6:8080"
    }
}