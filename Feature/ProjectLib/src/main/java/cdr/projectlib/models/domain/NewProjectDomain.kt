package cdr.projectlib.models.domain

/**
 * domain-модель с информацией о новом проекте
 *
 * @param name навзвание нового проекта
 * @param price цена нового проекта
 * @param description описание нового проекта
 * @param link ссылка на ресурс нового проекта
 *
 * @author Alexandr Chekunkov
 */
internal data class NewProjectDomain(
    val name: String,
    val price: Int,
    val description: String,
    val link: String
)