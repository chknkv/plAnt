package cdr.authorizationlib.data.interactor

import cdr.authorizationlib.data.repository.IdentificationRepository
import cdr.authorizationlib.models.domain.Authorization
import kotlinx.coroutines.delay

/**
 * Реализация [IdentificationInteractor]
 *
 * @author Alexandr Chekunkov
 */
internal class IdentificationInteractorImpl(
    private val identificationRepository: IdentificationRepository
) : IdentificationInteractor {

    override suspend fun signIn(authorizationData: Authorization) {
        identificationRepository.signIn(authorizationData)
    }

    override suspend fun signUp() {
        delay(1350) // удалить после выполнения задачи
        throw UnknownError("Нет доступа к удаленному сервису")
    }
}