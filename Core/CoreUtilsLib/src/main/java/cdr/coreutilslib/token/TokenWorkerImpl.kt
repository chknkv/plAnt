package cdr.coreutilslib.token

import android.content.Context
import com.auth0.android.jwt.JWT



/**
 * Реализация [TokenWorker]
 *
 * @author Alexandr Chekunkov
 */
class TokenWorkerImpl(private val context: Context) : TokenWorker {

    override fun setToken(token: String) {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(JWT_NAME, token)
        editor.apply()
    }

    override fun getToken(): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val jwtToken = sharedPreferences.getString(JWT_NAME, "")

        return BEARER + jwtToken
    }

    override fun getUsername(): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val jwtToken = sharedPreferences.getString(JWT_NAME, "") ?: UNKNOWN

        return JWT(jwtToken).getClaim("username").asString() ?: UNKNOWN
    }

    override fun getRole(): String {
        val sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
        val jwtToken = sharedPreferences.getString(JWT_NAME, "") ?: UNKNOWN

        return JWT(jwtToken).getClaim("role").asString() ?: UNKNOWN
    }

    companion object {
        private const val BEARER = "Bearer "
        private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_JWT"
        private const val JWT_NAME = "JWT_NAME"
        private const val UNKNOWN = "UNKNOWN"
    }

}