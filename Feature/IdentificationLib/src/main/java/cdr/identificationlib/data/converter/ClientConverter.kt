package cdr.identificationlib.data.converter

import cdr.identificationlib.models.data.ClientInfoResponse
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.ClientRoleResponse
import cdr.identificationlib.models.data.ClientDomain
import cdr.identificationlib.models.data.ClientInfo
import cdr.identificationlib.models.data.ClientRole

/**
 * Конвертер [ClientResponse] в [ClientDomain]
 *
 * @author Alexandr Chekunkov
 */
internal fun ClientResponse.toDomain(): ClientDomain =
    ClientDomain(
        client = this.client.toDomain(),
        token = this.token
    )

/**
 * Конвертер [ClientInfoResponse] в [ClientInfo]
 *
 * @author Alexandr Chekunkov
 */
internal fun ClientInfoResponse.toDomain(): ClientInfo =
    ClientInfo(
        login = this.login,
        name = this.name,
        surname = this.surname,
        role = this.role.toDomain()
    )

/**
 * Конвертер [ClientRoleResponse] в [ClientRole]
 *
 * @author Alexandr Chekunkov
 */
internal fun ClientRoleResponse.toDomain(): ClientRole = when (this) {
    ClientRoleResponse.DEVELOPER -> ClientRole.DEVELOPER
    ClientRoleResponse.QA -> ClientRole.QA
}