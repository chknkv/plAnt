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
            RestClientApp.PROFILE_APP -> ":8081"
            RestClientApp.PROJECT_APP -> ":8082"
            RestClientApp.REPORT_APP -> ":8083"
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL + port)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
            .build()
    }

    companion object {
        private const val BASE_URL = "http://uttsvlad.fvds.ru"
    }
}