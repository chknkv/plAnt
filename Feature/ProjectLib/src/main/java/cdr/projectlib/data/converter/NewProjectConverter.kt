package cdr.projectlib.data.converter

import cdr.projectlib.models.data.NewProjectRequest
import cdr.projectlib.models.data.ProjectApplicationInfoData
import cdr.projectlib.models.data.ProjectOperationSystemData
import cdr.projectlib.models.domain.NewProjectDomain
import cdr.projectlib.models.domain.ProjectApplicationInfoDomain
import cdr.projectlib.models.domain.ProjectOperationSystemDomain

/**
 * Конвертер из [NewProjectDomain] в [NewProjectRequest]
 *
 * @author Alexandr Chekunkov
 */
internal fun NewProjectDomain.toRequest(): NewProjectRequest = NewProjectRequest(
    name = this.name,
    price = this.price,
    description = this.description,
    applicationInfo = this.applicationInfo.toRequest()
)

/**
 * Конвертер из [ProjectApplicationInfoDomain] в [ProjectApplicationInfoData]
 *
 * @author Alexandr Chekunkov
 */
internal fun ProjectApplicationInfoDomain.toRequest(): ProjectApplicationInfoData =
    ProjectApplicationInfoData(
        operationSystem = this.operationSystem.toRequest(),
        url = this.url
    )

/**
 * Конвертер из [ProjectOperationSystemDomain] в [ProjectOperationSystemData]
 *
 * @author Alexandr Chekunkov
 */
internal fun ProjectOperationSystemDomain.toRequest(): ProjectOperationSystemData = when(this) {
    ProjectOperationSystemDomain.ANDROID -> ProjectOperationSystemData.ANDROID
    ProjectOperationSystemDomain.IOS -> ProjectOperationSystemData.IOS
}