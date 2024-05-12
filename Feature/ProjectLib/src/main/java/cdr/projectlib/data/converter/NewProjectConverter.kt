package cdr.projectlib.data.converter

import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.domain.NewProjectDomain

/**
 * Конвертер из [NewProjectDomain] в [NewProjectRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun NewProjectDomain.toRequest(): NewProjectRequest = NewProjectRequest(
    name = this.name,
    price = this.price,
    description = this.description,
    link = this.link
)