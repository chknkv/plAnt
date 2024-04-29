package cdr.identificationlib.data

import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.data.ClientInfoResponse
import cdr.identificationlib.models.data.ClientResponse
import cdr.identificationlib.models.data.ClientRoleResponse
import cdr.identificationlib.models.data.RegistrationRequest
import cdr.identificationlib.models.data.RoleRequest
import cdr.identificationlib.models.domain.AuthorizationDomain
import cdr.identificationlib.models.data.ClientDomain
import cdr.identificationlib.models.data.ClientInfo
import cdr.identificationlib.models.data.ClientRole
import cdr.identificationlib.models.domain.RegistrationDomain
import cdr.identificationlib.models.domain.RoleDomain

internal val clientDomain = ClientDomain(
    client = ClientInfo(
        login = "login",
        name = "name",
        surname = "surname",
        role = ClientRole.DEVELOPER
    ),
    token = "token"
)

internal val clientResponse = ClientResponse(
    client = ClientInfoResponse(
        login = "login",
        name = "name",
        surname = "surname",
        role = ClientRoleResponse.DEVELOPER
    ),
    token = "token"
)

internal val authorizationDomain = AuthorizationDomain(
    login = "login",
    password = "password"
)

internal val authorizationRequest = AuthorizationRequest(
    login = "login",
    password = "password"
)

internal val registrationDomain = RegistrationDomain(
    login = "login",
    password = "password",
    name = "name",
    surname = "surname",
    role = RoleDomain.QA
)

internal val registrationRequest = RegistrationRequest(
    login = "login",
    password = "password",
    name = "name",
    surname = "surname",
    role = RoleRequest.QA
)