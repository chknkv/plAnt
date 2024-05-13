package cdr.projectlib.models.domain

/**
 * domain-модель с информацией о новом проекте
 *
 * @param name навзвание нового проекта
 * @param price цена нового проекта
 * @param description описание нового проекта
 * @param applicationInfo domain-модель с информацией о типе устройства
 *
 * @author Alexandr Chekunkov
 */
internal data class NewProjectDomain(
    val name: String,
    val price: Double,
    val description: String,
    val applicationInfo: ProjectApplicationInfoDomain
)