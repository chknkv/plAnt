package cdr.identificationlib.data.converter

import cdr.identificationlib.data.registrationDomain
import cdr.identificationlib.data.registrationRequest
import cdr.identificationlib.models.data.RegistrationRequest
import cdr.identificationlib.models.domain.RegistrationDomain
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
}