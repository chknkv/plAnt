package cdr.coreutilslib.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Реализация [BaseRestClientFactory]
 *
 * @author Alexandr Chekunkov
 */
class BaseRestClientFactoryImpl : BaseRestClientFactory {

    override fun baseRestClient(app: RestClientApp): Retrofit {
        val port = when (app) {
            RestClientApp.PROFILE_APP -> ":8080"
            RestClientApp.PROJECT_APP -> ":8081"
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL + port)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()
    }

    companion object {
        private const val BASE_URL = "http://172.20.10.6"
    }
}