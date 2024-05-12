package cdr.profilelib.data.repository

import cdr.profilelib.data.converter.toDomain
import cdr.profilelib.data.converter.toRequest
import cdr.profilelib.data.mapper.ProfileMapper
import cdr.profilelib.models.domain.ClientDomain
import cdr.profilelib.models.domain.NewClientInfoDomain

/**
 * Реализация [ProfileRepository]
 *
 * @param profileMapper маппер для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileRepositoryImpl(
    private val profileMapper: ProfileMapper
) : ProfileRepository {

    override suspend fun getProfileInfo(): ClientDomain =
        profileMapper.getProfileInfo().toDomain()

    override suspend fun editClientInfo(newClientInfo: NewClientInfoDomain) =
        profileMapper.editClientInfo(newClientInfo.toRequest())
}