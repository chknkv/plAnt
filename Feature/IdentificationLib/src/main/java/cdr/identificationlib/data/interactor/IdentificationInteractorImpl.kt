package cdr.identificationlib.data.interactor

import cdr.identificationlib.data.repository.IdentificationRepository
import cdr.identificationlib.models.domain.AuthorizationDomain
import cdr.identificationlib.models.domain.ClientDomain
import cdr.identificationlib.models.domain.RegistrationDomain

/**
 * Реализация [IdentificationInteractor]
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationInteractorImpl(
    private val identificationRepository: IdentificationRepository
) : IdentificationInteractor {

    override suspend fun signIn(authorizationDomain: AuthorizationDomain): ClientDomain =
        identificationRepository.signIn(authorizationDomain)

    override suspend fun signUp(registrationDomain: RegistrationDomain): ClientDomain =
        identificationRepository.signUp(registrationDomain)
}