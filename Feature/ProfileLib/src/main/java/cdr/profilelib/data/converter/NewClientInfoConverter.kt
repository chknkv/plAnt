package cdr.profilelib.data.converter

import cdr.profilelib.models.data.NewClientInfoRequest
import cdr.profilelib.models.data.NewClientRoleRequest
import cdr.profilelib.models.domain.NewClientInfoDomain
import cdr.profilelib.models.domain.NewClientRole

/**
 * Конвертер из [NewClientInfoDomain] в [NewClientInfoRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun NewClientInfoDomain.toRequest(): NewClientInfoRequest = NewClientInfoRequest(
    name = this.name,
    surname = this.surname,
    role = this.role.toRequest(),
    newPassword = this.newPassword
)

/**
 * Конвертер из [NewClientRole] в [NewClientRoleRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun NewClientRole.toRequest(): NewClientRoleRequest = when (this) {
    NewClientRole.QA -> NewClientRoleRequest.QA
    NewClientRole.DEVELOPER -> NewClientRoleRequest.DEVELOPER
}