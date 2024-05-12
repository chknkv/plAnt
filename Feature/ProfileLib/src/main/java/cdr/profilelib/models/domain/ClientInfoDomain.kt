package cdr.profilelib.models.domain

import android.os.Parcelable
import cdr.projectlib.models.domain.ProjectInfoDomain
import kotlinx.parcelize.Parcelize

/**
 * domain-модель, содержащая в себе данные о профиле клиента
 *
 * @param clientInfo UI-модель с информацией о клиента
 * @param projectInfoList список UI-моделей с информацией о проекте клиента
 *
 * @author Alexandr Chekunkov
 */
internal data class ClientDomain(
    val clientInfo: ClientInfoDomain,
    val projectInfoList: List<ProjectInfoDomain>
)

/**
 * domain-модель с информацией о клиенте
 *
 * @property firstName имя клиента
 * @property lastName фамилия клиента
 * @property username логин клиента
 * @property role роль клиента
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
internal data class ClientInfoDomain(
    val firstName: String,
    val lastName: String,
    val username: String,
    val role: ClientInfoRoleDomain
) : Parcelable

/**
 * domain-модель с новой ролью клиента (тестировщик или разработчик)
 *
 * @author Alexandr Chekunkov
 */
internal enum class ClientInfoRoleDomain {
    /** Тестировщик */
    QA,

    /** Разработчик */
    DEVELOPER
}
