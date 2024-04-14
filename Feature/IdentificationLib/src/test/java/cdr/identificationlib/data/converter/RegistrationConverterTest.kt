package cdr.identificationlib.data.converter

import cdr.identificationlib.models.data.RegistrationRequest
import cdr.identificationlib.models.data.RoleRequest
import cdr.identificationlib.models.domain.RegistrationDomain
import cdr.identificationlib.models.domain.RoleDomain
import com.google.common.truth.Truth
import org.junit.jupiter.api.Test

/**
 * Тест конвертера [RegistrationDomain] в [RegistrationRequest]
 *
 * @author Alexandr Chekunkov
 */
internal class RegistrationConverterTest {

    @Test
    fun `convert registration domain to registration request`() {
        val expected = registrationRequest

        val actual = registrationDomain.toRequest()

        Truth.assertThat(actual).isEqualTo(expected)
    }

    private val registrationDomain = RegistrationDomain(
        login = "login",
        password = "password",
        name = "name",
        surname = "surname",
        role = RoleDomain.DEVELOPER
    )

    private val registrationRequest = RegistrationRequest(
        login = "login",
        password = "password",
        name = "name",
        surname = "surname",
        role = RoleRequest.DEVELOPER
    )
}