package cdr.identificationlib.data.repository

import cdr.identificationlib.data.converter.toRequest
import cdr.identificationlib.data.mapper.IdentificationMapper
import cdr.identificationlib.models.domain.AuthorizationDomain
import cdr.identificationlib.models.domain.RegistrationDomain

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