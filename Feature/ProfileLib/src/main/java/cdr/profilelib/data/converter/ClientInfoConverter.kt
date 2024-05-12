package cdr.profilelib.data.converter

import cdr.profilelib.models.data.ClientInfoResponse
import cdr.profilelib.models.data.ClientInfoRoleResponse
import cdr.profilelib.models.data.ClientResponse
import cdr.profilelib.models.domain.ClientDomain
import cdr.profilelib.models.domain.ClientInfoDomain
import cdr.profilelib.models.domain.ClientInfoRoleDomain
import cdr.projectlib.data.converter.toDomain

/**
 * Конвертер из [ClientResponse] в [ClientDomain]
 *
 * @author Alexandr Chekunkov
 */
internal fun ClientResponse.toDomain(): ClientDomain = ClientDomain(
    clientInfo = this.clientInfo.toDomain() ,
    projectInfoList = this.clientProjects.map { projectInfo -> projectInfo.toDomain() }
)

/**
 * Конвертер из [ClientInfoResponse] в [ClientInfoDomain]
 *
 * @author Alexandr Chekunkov
 */
internal fun ClientInfoResponse.toDomain(): ClientInfoDomain = ClientInfoDomain(
    firstName = this.firstName ?: "",
    lastName = this.lastName ?: "",
    username = this.username ?: "",
    role = this.role?.toDomain() ?: ClientInfoRoleDomain.DEVELOPER
)

/**
 * Конвертер из [ClientInfoRoleResponse] в [ClientInfoRoleDomain]
 *
 * @author Alexandr Chekunkov
 */
internal fun ClientInfoRoleResponse.toDomain(): ClientInfoRoleDomain = when(this) {
    ClientInfoRoleResponse.QA -> ClientInfoRoleDomain.QA
    ClientInfoRoleResponse.DEVELOPER -> ClientInfoRoleDomain.DEVELOPER
}
