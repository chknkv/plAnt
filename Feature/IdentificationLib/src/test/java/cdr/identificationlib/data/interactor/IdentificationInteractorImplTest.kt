package cdr.identificationlib.data.interactor

import cdr.identificationlib.data.authorizationDomain
import cdr.identificationlib.data.clientDomain
import cdr.identificationlib.data.registrationDomain
import cdr.identificationlib.data.repository.IdentificationRepository
import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

/**
 * Тест для [IdentificationInteractorImpl]
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationInteractorImplTest {

    private val identificationRepository = mockk<IdentificationRepository>()
    private val identificationInteractor = IdentificationInteractorImpl(identificationRepository)

    @Test
    fun `sign in`() = runTest {
        val expectedClient = clientDomain
        coEvery { identificationInteractor.signIn(authorizationDomain) } returns clientDomain

        val actualClient = identificationInteractor.signIn(authorizationDomain)

        assertThat(actualClient).isEqualTo(expectedClient)
        coVerify { identificationInteractor.signIn(authorizationDomain) }
    }

    @Test
    fun `sign up`() = runTest {
        val expectedClient = clientDomain
        coEvery { identificationInteractor.signUp(registrationDomain) } returns clientDomain

        val actualClient = identificationInteractor.signUp(registrationDomain)

        assertThat(actualClient).isEqualTo(expectedClient)
        coVerify { identificationInteractor.signUp(registrationDomain) }
    }
}