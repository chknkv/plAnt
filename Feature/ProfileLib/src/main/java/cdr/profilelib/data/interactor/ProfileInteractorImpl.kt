package cdr.profilelib.data.interactor

import cdr.profilelib.data.repository.ProfileRepository
import cdr.profilelib.models.domain.ClientDomain
import cdr.profilelib.models.domain.NewClientInfoDomain

/**
 * Реализация [ProfileInteractor]
 *
 * @param profileRepository репозиторий для функционала модуля проектов
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileInteractorImpl(
    private val profileRepository: ProfileRepository
) : ProfileInteractor {

    override suspend fun getProfileInfo(): ClientDomain =
        profileRepository.getProfileInfo()

    override suspend fun editClientInfo(newClientInfo: NewClientInfoDomain) =
        profileRepository.editClientInfo(newClientInfo)
}