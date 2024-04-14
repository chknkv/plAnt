package cdr.identificationlib.data.converter

import cdr.identificationlib.models.data.AuthorizationRequest
import cdr.identificationlib.models.domain.AuthorizationDomain


/**
 * Конвертер [AuthorizationDomain] в [AuthorizationRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun AuthorizationDomain.toRequest(): AuthorizationRequest =
    AuthorizationRequest(
        login = this.login,
        password = this.password
    )