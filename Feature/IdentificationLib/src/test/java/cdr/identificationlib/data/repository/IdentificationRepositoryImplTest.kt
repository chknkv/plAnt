package cdr.identificationlib.data.repository

import cdr.coreutilslib.token.TokenWorker
import cdr.identificationlib.data.authorizationDomain
import cdr.identificationlib.data.authorizationRequest
import cdr.identificationlib.data.clientDomain
import cdr.identificationlib.data.clientResponse
import cdr.identificationlib.data.converter.toDomain
import cdr.identificationlib.data.converter.toRequest
import cdr.identificationlib.data.mapper.IdentificationMapper
import cdr.identificationlib.data.registrationDomain
import cdr.identificationlib.data.registrationRequest
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerifySequence
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

/**
 * Тест для [IdentificationRepositoryImpl]
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationRepositoryImplTest {

    private val identificationMapper = mockk<IdentificationMapper>()
    private val tokenWorker = mockk<TokenWorker>()
    private val identificationRepository = IdentificationRepositoryImpl(identificationMapper, tokenWorker)

    @Test
    fun `sign in`() = runTest {
        val expectedClient = clientDomain
        val authorizationDomain = authorizationDomain
        val authorizationRequest = authorizationRequest
        val clientResponse = clientResponse
        coEvery { identificationMapper.signIn(authorizationRequest) } returns clientResponse

        val actualClient = identificationRepository.signIn(authorizationDomain)

        assertThat(actualClient).isEqualTo(expectedClient)
        coVerifySequence {
            authorizationDomain.toRequest()
            identificationMapper.signIn(authorizationRequest)
            clientResponse.toDomain()
        }
    }

    @Test
    fun `sign up`() = runTest {
        val expectedClient = clientDomain
        val registrationDomain = registrationDomain
        val registrationRequest = registrationRequest
        val clientResponse = clientResponse
        coEvery { identificationMapper.signUp(registrationRequest) } returns clientResponse

        val actualClient = identificationRepository.signUp(registrationDomain)

        assertThat(actualClient).isEqualTo(expectedClient)
        coVerifySequence {
            registrationDomain.toRequest()
            identificationMapper.signUp(registrationRequest)
            clientResponse.toDomain()
        }
    }
}