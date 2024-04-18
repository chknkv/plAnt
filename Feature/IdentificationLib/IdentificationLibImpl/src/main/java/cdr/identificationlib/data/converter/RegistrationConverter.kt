package cdr.identificationlib.data.converter

import cdr.identificationlib.models.data.RegistrationRequest
import cdr.identificationlib.models.data.RoleRequest
import cdr.identificationlib.models.domain.RegistrationDomain
import cdr.identificationlib.models.domain.RoleDomain

/**
 * Конвертер [RegistrationDomain] в [RegistrationRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun RegistrationDomain.toRequest(): RegistrationRequest =
    RegistrationRequest(
        login = this.login,
        password = this.password,
        name = this.name,
        surname = this.surname,
        role = this.role.toRequest()
    )

/**
 * Конвертер [RoleDomain] в [RoleRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun RoleDomain.toRequest(): RoleRequest = when (this) {
    RoleDomain.DEVELOPER -> RoleRequest.DEVELOPER
    RoleDomain.QA -> RoleRequest.QA
}