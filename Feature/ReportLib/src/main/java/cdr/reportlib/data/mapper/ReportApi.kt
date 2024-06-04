package cdr.reportlib.data.mapper

import cdr.reportlib.models.data.NewReportRequest
import cdr.reportlib.models.data.ReportInfoResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


/**
 * API-интерфейс для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal interface ReportApi {

    /** Создание нового проекта */
    @POST("/bugs/create")
    suspend fun saveNewReport(
        @Header("Authorization") jwtToken: String,
        @Body newReport: NewReportRequest
    )

    @GET("/bugs")
    suspend fun getReportInfo(
        @Header("Authorization") jwtToken: String,
        @Query("project_name") projectName: String
    ): List<ReportInfoResponse>

    @PUT("/projects/close")
    suspend fun closeProject(
        @Header("Authorization") jwtToken: String,
        @Query("project_name") projectName: String
    )
}
