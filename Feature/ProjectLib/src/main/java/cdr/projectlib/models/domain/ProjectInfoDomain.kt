package cdr.projectlib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain-модель с информацией о проекте
 *
 * @property id уникальный номер проекта
 * @property name название проекта
 * @property author логин автора проекта
 * @property status статус проекта на текущий момент (неизвестно, открыт, в работе, закрыт)
 * @property description описание проекта
 * @property applicationInfo domain-модель с информацией о типе устройства
 * @property price цена за выполнение проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
data class ProjectInfoDomain(
    val id: Int = -1,
    val name: String = "",
    val author: String = "",
    val status: ProjectStatusDomain = ProjectStatusDomain.UNKNOWN,
    val description: String = "",
    val applicationInfo: ProjectApplicationInfoDomain? = null,
    val price: Double = -1.0
) : Parcelable

/**
 * domain-модель с информацией о типе устройства
 *
 * @property operationSystem тип операционной системы мобильного устройства
 * @property url ссылка на скачивание приложения
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
data class ProjectApplicationInfoDomain(
    val operationSystem: ProjectOperationSystemDomain,
    val url: String
) : Parcelable

/**
 * Тип операционной системы мобильного устройства
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
enum class ProjectOperationSystemDomain : Parcelable {

    /** OC Android */
    ANDROID,

    /** OC iOS */
    IOS
}

/**
 * Статусы выполнения проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
enum class ProjectStatusDomain : Parcelable {

    /** Неизвестно */
    UNKNOWN,

    /** Открыт */
    OPEN,

    /** В работе */
    IN_WORK,

    /** Закрыт */
    CLOSED,
}