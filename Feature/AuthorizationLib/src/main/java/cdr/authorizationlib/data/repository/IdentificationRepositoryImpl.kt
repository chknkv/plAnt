package cdr.authorizationlib.data.repository

import cdr.authorizationlib.data.converter.toRequest
import cdr.authorizationlib.data.mapper.IdentificationMapper
import cdr.authorizationlib.models.domain.AuthorizationDomain
import cdr.authorizationlib.models.domain.RegistrationDomain

/**
 * Реализация [IdentificationRepository]
 *
 * @param identificationMapper маппер для функционала модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationRepositoryImpl(
    private val identificationMapper: IdentificationMapper
) : IdentificationRepository{

    override suspend fun signIn(authorizationDomain: AuthorizationDomain) {
        identificationMapper.signIn(authorizationDomain.toRequest())
    }

    override suspend fun signUp(registrationDomain: RegistrationDomain) {
        identificationMapper.signUp(registrationDomain.toRequest())
    }

}