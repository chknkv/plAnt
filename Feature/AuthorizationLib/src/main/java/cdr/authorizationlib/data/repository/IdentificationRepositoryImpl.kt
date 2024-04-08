package cdr.authorizationlib.data.repository

import cdr.authorizationlib.data.converter.toRequest
import cdr.authorizationlib.data.mapper.IdentificationMapper
import cdr.authorizationlib.models.domain.Authorization

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

    override suspend fun signIn(authorizationData: Authorization) {
        identificationMapper.signIn(authorizationData.toRequest())
    }

}