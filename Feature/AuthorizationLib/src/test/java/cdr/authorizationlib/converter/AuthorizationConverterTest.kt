package cdr.authorizationlib.converter

import cdr.authorizationlib.data.converter.toRequest
import cdr.authorizationlib.models.data.AuthorizationRequest
import cdr.authorizationlib.models.domain.AuthorizationDomain
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * Тест конвертера [AuthorizationDomain] в [AuthorizationRequest]
 *
 * @author Alexandr Chekunkov
 */
internal class AuthorizationConverterTest {

    @Test
    fun `convert authorization domain to authorization request`() {
        val expected = expectedRequest

        val actual = authorizationDomain.toRequest()

        assertThat(actual).isEqualTo(expected)
    }

    private val authorizationDomain = AuthorizationDomain(
        login = "testUser",
        password = "pass"
    )

    private val expectedRequest = AuthorizationRequest(
        login = "testUser",
        password = "pass"
    )
}