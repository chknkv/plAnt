package cdr.authorizationlib.data.converter

import cdr.authorizationlib.models.data.AuthorizationRequest
import cdr.authorizationlib.models.domain.Authorization

/**
 * Конвертер [Authorization] в [AuthorizationRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun Authorization.toRequest(): AuthorizationRequest =
    AuthorizationRequest(
        login = this.login,
        password = this.password
    )