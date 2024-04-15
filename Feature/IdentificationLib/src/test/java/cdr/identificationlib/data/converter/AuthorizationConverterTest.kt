package cdr.identificationlib.data.converter

import cdr.identificationlib.data.authorizationDomain
import cdr.identificationlib.data.authorizationRequest
import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.domain.AuthorizationDomain
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
        val expected = authorizationRequest

        val actual = authorizationDomain.toRequest()

        assertThat(actual).isEqualTo(expected)
    }
}