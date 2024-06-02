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
    private val client = restClientFactory
        .baseRestClient(RestClientApp.REPORT_APP)
        .create(ReportApi::class.java)

    override suspend fun saveNewReport(newReport: NewReportRequest) =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[newReport] bodyRequest: $newReport")
            client.saveNewReport(token, newReport)
        }

    override suspend fun getReportInfo(projectId: Int): List<ReportInfoResponse> =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[getReportInfo] projectId: $projectId")
            val allReports = getMockedAllReportInfo()
//            val allReports = client.getReportInfo(token, projectId)

            return@withContext allReports
        }

    override suspend fun closeProject(projectId: Int) =
        withContext(Dispatchers.IO) {
            Logger.i(TAG, "[closeProject] projectId: $projectId")
            client.closeProject(token, projectId)
        }

    override suspend fun changePaymentStatus(reportId: Int) {
        TODO("Not yet implemented")
    }

    companion object {
        private const val TAG = "ReportMapper"
    }
}