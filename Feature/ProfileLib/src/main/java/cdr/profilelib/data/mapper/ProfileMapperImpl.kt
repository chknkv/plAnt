package cdr.profilelib.data.mapper

import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.data.NewClientInfoRequest
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Реализация [ProfileMapper]
 *
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileMapperImpl : ProfileMapper {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().serializeNulls().create()))
        .build()
    private val client = retrofit.create(ProfileApi::class.java)

    override suspend fun getProfileInfo(): ClientResponse =
        withContext(Dispatchers.IO) {
            return@withContext client.getProfileInfo(MOCKED_JWT_TOKEN)
        }


    override suspend fun editClientInfo(newClientInfo: NewClientInfoRequest) =
        withContext(Dispatchers.IO) {
            client.editClientInfo(MOCKED_JWT_TOKEN, newClientInfo)
        }

    companion object {
        private const val BASE_URL = "http://172.20.10.6:8080"
        private const val MOCKED_JWT_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJVc2VyIGRldGFpbHMiLCJ1c2VybmFtZSI6InV0dHN2bGFkX25ldyIsImlhdCI6MTcxNTc4NzQ4MCwiaXNzIjoicGxBbnQiLCJleHAiOjE3MTU4NzM4ODB9.HNar0yE17I5RvAUxPbQVP8HjKCEZF3xkvYhzUpoCVKw"
    }
}