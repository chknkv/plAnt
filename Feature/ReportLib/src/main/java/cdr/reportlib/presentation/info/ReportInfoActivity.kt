package cdr.reportlib.presentation.info

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.getParcelableObject
import cdr.reportlib.models.domain.ProjectInfoDomain

/**
 * Экран с информацией об ошибках
 *
 * @author Alexandr Chekunkov
 */
class ReportInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val projectInfo: ProjectInfoDomain = intent.getParcelableObject(TAG) ?: error("projectInfo не найден")
        setContent {
            PlAntTheme {
                ReportInfoContent(
                    onFinishActivity = { this.finish() },
                    projectInfo = projectInfo
                )
            }
        }
    }

    companion object {

        private const val TAG = "project_info"

        /**
         * Создание [Intent] для запуска [ReportInfoActivity]
         *
         * @param context контекст
         * @param projectInfo модель с информацией о проекте
         */
        fun newIntent(
            context: Context,
            projectInfo: ProjectInfoDomain
        ): Intent = Intent(context, ReportInfoActivity::class.java).putExtra(TAG, projectInfo)
    }
}