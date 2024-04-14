package cdr.identificationlib.data.converter

import cdr.identificationlib.models.data.ClientInfoResponse
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.ClientRoleResponse
import cdr.identificationlib.models.domain.ClientDomain
import cdr.identificationlib.models.domain.ClientInfo
import cdr.identificationlib.models.domain.ClientRole
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

    private val clientDomain = ClientDomain(
        client = ClientInfo(
            login = "login",
            name = "name",
            surname = "surname",
            role = ClientRole.DEVELOPER
        ),
        token = "jwt"
    )

    private val clientResponse = ClientResponse(
        client = ClientInfoResponse(
            login = "login",
            name = "name",
            surname = "surname",
            role = ClientRoleResponse.DEVELOPER
        ),
        token = "jwt"
    )
}