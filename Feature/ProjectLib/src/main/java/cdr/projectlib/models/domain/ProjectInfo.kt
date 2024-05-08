package cdr.projectlib.models.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Модель с информацией о проекте
 *
 * @param id уникальный номер проекта
 * @param name название проекта
 * @param author логин автора проекта
 * @param status статус проекта на текущий момент (неизвестно, открыт, в работе, закрыт)
 * @param description описание проекта
 * @param price цена за выполнение проекта
 * @param isHaveExecutor назначен ли исполнитель проекта
 * @param executor логин исполнителя проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
data class ProjectInfo(
    val id: Int = -1,
    val name: String = "",
    val author: String = "",
    val status: ProjectStatus = ProjectStatus.UNKNOWN,
    val description: String = "",
    val price: Int = -1,
    val isHaveExecutor: Boolean = false,
    val executor: String = ""
) : Parcelable

/**
 * Статусы выполнения проекта
 *
 * @author Alexandr Chekunkov
 */
@Parcelize
enum class ProjectStatus : Parcelable{

    /** Неизвестно */
    UNKNOWN,

    /** Открыт */
    OPEN,

    /** В работе */
    IN_WORK,

    /** Закрыт */
    CLOSED,
}