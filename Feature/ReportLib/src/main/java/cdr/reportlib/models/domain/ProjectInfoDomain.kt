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
    val author: String = ""
) : Parcelable