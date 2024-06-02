package cdr.reportlib.data.mapper

import cdr.reportlib.models.data.NewReportRequest
import cdr.reportlib.models.data.ReportInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


/**
 * API-интерфейс для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal interface ReportApi {

    /** Создание нового проекта */
    @POST("/reports/newReport")
    suspend fun saveNewReport(
        @Header("Authorization") jwtToken: String,
        @Body newProject: NewReportRequest
    )

    @GET("/reports/allReports/{projectId}")
    suspend fun getReportInfo(
        @Header("Authorization") jwtToken: String,
        @Query("projectId") projectId: Int
    ): List<ReportInfoResponse>

    @GET("/projects/close/{projectId}")
    suspend fun closeProject(
        @Header("Authorization") jwtToken: String,
        @Query("projectId") projectId: Int
    )
}
