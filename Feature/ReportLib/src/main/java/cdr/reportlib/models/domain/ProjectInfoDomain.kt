package cdr.reportlib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * domain-модель с информацией о проекте (уменьшенная версия)
 *
 * @property id уникальный номер проекта
 * @property name название проекта
 * @property author логин автора проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
data class ProjectInfoDomain(
    val id: Int = -1,
    val name: String = "",
    val author: String = "",
    val status: ProjectStatusDomain = ProjectStatusDomain.UNKNOWN
) : Parcelable

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