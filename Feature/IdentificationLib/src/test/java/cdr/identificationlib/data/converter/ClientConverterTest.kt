package cdr.identificationlib.data.converter

import cdr.identificationlib.data.clientDomain
import cdr.identificationlib.data.clientResponse
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.domain.ClientDomain
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.api.Test

/**
 * Тест конвертера [ClientResponse] в [ClientDomain]
 *
 * @author Alexandr Chekunkov
 */
internal class ClientConverterTest {

    @Test
    fun `convert client response to client domain`() {
        val expected = clientDomain

        val actual = clientResponse.toDomain()

        assertThat(actual).isEqualTo(expected)
    }
}