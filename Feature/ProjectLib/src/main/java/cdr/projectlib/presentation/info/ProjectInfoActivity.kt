package cdr.projectlib.presentation.info

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.getSerializableObject
import cdr.projectlib.models.domain.ProjectInfoDomain

/**
 * Activity, которая держит шторку с информацией о проекте
 *
 * @author Alexandr Chekunkov
 */
class ProjectInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        } else {
            android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }

        val projectInfo: ProjectInfoDomain = intent.getSerializableObject(TAG) ?: error("projectInfo не найден")

        setContent {
            PlAntTheme {
                ProjectInfoContent(
                    onFinishActivity = { this.finish() },
                    projectInfo = projectInfo
                )
            }
        }
    }

    companion object {

        private const val TAG = "project_info"

        /**
         * Создание [Intent] для запуска [ProjectInfoActivity]
         *
         * @param context контекст
         * @param projectInfo модель с информацией о проекте
         */
        fun newIntent(
            context: Context,
            projectInfo: ProjectInfoDomain
        ): Intent = Intent(context, ProjectInfoActivity::class.java).putExtra(TAG, projectInfo)
    }
}