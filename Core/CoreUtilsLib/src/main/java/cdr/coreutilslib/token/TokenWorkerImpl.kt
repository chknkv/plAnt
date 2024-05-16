package cdr.coreutilslib.token

/**
 * Реализация [TokenWorker]
 *
 * @author Alexandr Chekunkov
 */
class TokenWorkerImpl : TokenWorker {

    override fun setToken(token: String) {
        // TODO: unused now
    }

    override fun getToken(): String {
        // TODO: fix

        return BEARER + MOCKED_JWT_TOKEN
    }

    companion object {
        private const val BEARER = "Bearer"
        private const val MOCKED_JWT_TOKEN = ""
    }

}