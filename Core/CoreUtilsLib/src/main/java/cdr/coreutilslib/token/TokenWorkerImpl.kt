package cdr.coreutilslib.token

import android.content.Context

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

    companion object {
        private const val BEARER = "Bearer "
        private const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES_JWT"
        private const val JWT_NAME = "JWT_NAME"
    }

}