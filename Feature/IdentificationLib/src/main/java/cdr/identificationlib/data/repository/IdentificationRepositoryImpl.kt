package cdr.identificationlib.data.repository

import cdr.coreutilslib.token.TokenWorker
import cdr.identificationlib.data.converter.toDomain
import cdr.identificationlib.data.converter.toRequest
import cdr.identificationlib.data.mapper.IdentificationMapper
import cdr.identificationlib.models.domain.AuthorizationDomain
import cdr.identificationlib.models.data.ClientDomain
import cdr.identificationlib.models.domain.RegistrationDomain

/**
 * Реализация [IdentificationRepository]
 *
 * @param identificationMapper маппер для функционала модуля авторизации
 * @param tokenWorker воркер для работы с токеном
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationRepositoryImpl(
    private val identificationMapper: IdentificationMapper,
    private val tokenWorker: TokenWorker
) : IdentificationRepository {

    override suspend fun signIn(authorizationDomain: AuthorizationDomain): ClientDomain {
        val clientInfo = identificationMapper.signIn(authorizationDomain.toRequest()).toDomain()
        tokenWorker.setToken(clientInfo.token)

        return clientInfo
    }

    override suspend fun signUp(registrationDomain: RegistrationDomain): ClientDomain {
        val clientInfo = identificationMapper.signUp(registrationDomain.toRequest()).toDomain()
        tokenWorker.setToken(clientInfo.token)

        return clientInfo
    }
}