package cdr.reportlib.presentation.report

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.getParcelableObject
import cdr.reportlib.models.domain.ProjectInfoDomain

/**
 * Activity для экрана создания репорта об найденных ошибках
 *
 * @author Alexandr Chekunkov
 */
class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val projectInfo: ProjectInfoDomain = intent.getParcelableObject(TAG) ?: error("projectInfo не найден")
        setContent {
            PlAntTheme {
                ReportContent(
                    onFinishActivity = { this.finish() },
                    projectInfo = projectInfo
                )
            }
        }
    }

    companion object {

        private const val TAG = "project_info"

        /**
         * Создание [Intent] для запуска [ReportActivity]
         *
         * @param context контекст
         * @param projectInfo модель с информацией о проекте
         */
        fun newIntent(
            context: Context,
            projectInfo: ProjectInfoDomain
        ): Intent = Intent(context, ReportActivity::class.java).putExtra(TAG, projectInfo)
    }
}