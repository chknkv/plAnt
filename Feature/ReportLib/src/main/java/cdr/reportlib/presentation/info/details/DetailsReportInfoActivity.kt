package cdr.reportlib.presentation.info.details

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.getParcelableObject
import cdr.reportlib.models.domain.ReportInfoDomain

/**
 * Activity, которая держит шторку с информацией о баге-репорте
 *
 * @author Alexandr Chekunkov
 */
internal class DetailsReportInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val reportInfo: ReportInfoDomain =
            intent.getParcelableObject(TAG) ?: error("report_info не найден")
        setContent {
            PlAntTheme {
                DetailsReportInfoContent(
                    onFinishActivity = { this.finish() },
                    reportInfo = reportInfo
                )
            }
        }
    }

    companion object {

        private const val TAG = "report_info"

        /**
         * Создание [Intent] для запуска [DetailsReportInfoActivity]
         *
         * @param context контекст
         * @param projectInfo модель с информацией о проекте
         */
        fun newIntent(
            context: Context,
            projectInfo: ReportInfoDomain
        ): Intent = Intent(context, DetailsReportInfoActivity::class.java).putExtra(TAG, projectInfo)
    }
}