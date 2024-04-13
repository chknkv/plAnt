package cdr.authorizationlib.data.converter

import cdr.authorizationlib.models.data.AuthorizationRequest
import cdr.authorizationlib.models.domain.AuthorizationDomain

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