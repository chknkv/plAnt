package cdr.reportlib.data.mapper

import cdr.coreutilslib.logs.Logger
import cdr.coreutilslib.network.BaseRestClientFactory
import cdr.coreutilslib.network.RestClientApp
import cdr.coreutilslib.token.TokenWorker
import cdr.reportlib.models.data.NewReportRequest
import cdr.reportlib.models.data.ReportInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Реализация [ReportMapper]
 *
 * @param restClientFactory фабрика для создания клиента сетевого взаимодействия
 * @param tokenWorker воркер для работы с токеном
 *
 * @author Alexandr Chekunkov
 */
internal class ReportMapperImpl(
    private val restClientFactory: BaseRestClientFactory,
    private val tokenWorker: TokenWorker
) : ReportMapper {

    private val token = tokenWorker.getToken()
    private val clientReport = restClientFactory
        .baseRestClient(RestClientApp.REPORT_APP)
        .create(ReportApi::class.java)
    private val clientProject = restClientFactory
        .baseRestClient(RestClientApp.PROJECT_APP)
        .create(ReportApi::class.java)

    override suspend fun saveNewReport(newReport: NewReportRequest) =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[newReport] bodyRequest: $newReport")
            clientReport.saveNewReport(token, newReport)
        }

    override suspend fun getReportInfo(projectName: String): List<ReportInfoResponse> =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[getReportInfo] projectName: $projectName")
//            val allReports = getMockedAllReportInfo()
            val allReports = clientReport.getReportInfo(token, projectName)

            return@withContext allReports
        }

    override suspend fun closeProject(projectName: String) =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[closeProject] projectName: $projectName")
            clientProject.closeProject(token, projectName)
        }

    override suspend fun changePaymentStatus(reportId: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "ReportMapper"
    }
}