package cdr.projectlib.data.converter

import cdr.projectlib.models.data.ProjectApplicationInfoData
import cdr.projectlib.models.data.ProjectInfoResponse
import cdr.projectlib.models.data.ProjectOperationSystemData
import cdr.projectlib.models.data.ProjectStatusResponse
import cdr.projectlib.models.domain.ProjectApplicationInfoDomain
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.models.domain.ProjectOperationSystemDomain
import cdr.projectlib.models.domain.ProjectStatusDomain

/**
 * Конвертер из [ProjectInfoResponse] в [ProjectInfoDomain]
 *
 * @author Alexandr Chekunkov
 */
fun ProjectInfoResponse.toDomain(): ProjectInfoDomain = ProjectInfoDomain(
    id = this.id ?: -1,
    author = this.author ?: "",
    name = this.name ?: "",
    status = this.status?.toDomain() ?: ProjectStatusDomain.UNKNOWN,
    description = this.description ?: "",
    applicationInfo = this.applicationInfo?.toDomain(),
    price = this.price ?: -1.0
)

/**
 * Конвертер из [ProjectApplicationInfoData] в [ProjectApplicationInfoDomain]
 *
 * @author Alexandr Chekunkov
 */
fun ProjectApplicationInfoData.toDomain() : ProjectApplicationInfoDomain = ProjectApplicationInfoDomain(
    operationSystem = this.operationSystem.toDomain(),
    url = this.url
)

/**
 * Конвертер из [ProjectOperationSystemData] в [ProjectOperationSystemDomain]
 *
 * @author Alexandr Chekunkov
 */
fun ProjectOperationSystemData.toDomain() : ProjectOperationSystemDomain = when(this) {
    ProjectOperationSystemData.ANDROID -> ProjectOperationSystemDomain.ANDROID
    ProjectOperationSystemData.IOS -> ProjectOperationSystemDomain.IOS
}

/**
 * Конвертер из [ProjectStatusResponse] в [ProjectStatusDomain]
 *
 * @author Alexandr Chekunkov
 */
internal fun ProjectStatusResponse.toDomain(): ProjectStatusDomain = when (this) {
    ProjectStatusResponse.UNKNOWN -> ProjectStatusDomain.UNKNOWN
    ProjectStatusResponse.OPEN -> ProjectStatusDomain.OPEN
    ProjectStatusResponse.IN_WORK -> ProjectStatusDomain.IN_WORK
    ProjectStatusResponse.CLOSED -> ProjectStatusDomain.CLOSED
}