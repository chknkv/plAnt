package cdr.authorizationlib.data.interactor

import cdr.authorizationlib.data.repository.IdentificationRepository
import cdr.authorizationlib.models.domain.AuthorizationDomain
import cdr.authorizationlib.models.domain.RegistrationDomain

/**
 * Реализация [IdentificationInteractor]
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationInteractorImpl(
    private val identificationRepository: IdentificationRepository
) : IdentificationInteractor {

    override suspend fun signIn(authorizationDomain: AuthorizationDomain) {
        identificationRepository.signIn(authorizationDomain)
    }

    override suspend fun signUp(registrationDomain: RegistrationDomain) {
        identificationRepository.signUp(registrationDomain)
    }
}