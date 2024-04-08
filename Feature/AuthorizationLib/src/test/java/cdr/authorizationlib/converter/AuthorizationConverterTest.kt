package cdr.authorizationlib.converter

import cdr.authorizationlib.data.converter.toRequest
import cdr.authorizationlib.models.data.AuthorizationRequest
import cdr.authorizationlib.models.domain.Authorization
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * Тест конвертера [Authorization] в [AuthorizationRequest]
 *
 * @author Alexandr Chekunkov
 */
internal class AuthorizationConverterTest {

    @Test
    fun `convert authorization to authorization request`() {

        val actual = authorizationData.toRequest()

        assertThat(actual).isEqualTo(expectedRequest)
    }

    private val authorizationData = Authorization(
        login = "testUser",
        password = "pass"
    )

    private val expectedRequest = AuthorizationRequest(
        login = "testUser",
        password = "pass"
    )
}