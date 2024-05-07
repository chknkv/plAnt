package cdr.mainscreenlib.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdr.corecompose.text.Body2
import cdr.corecompose.text.Body3
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.mainscreenlib.models.presentation.ClientProjectInfo
import cdr.mainscreenlib.models.presentation.ProjectStatus
import cdr.mainscreenlibimpl.R

/**
 * Контент со списком проектов клиента
 *
 * @param data список моделей с информацией о проекте клиента
 * @param onProjectClick действие по нажатию на карточку проекта
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ClientProjectsInformationContent(
    data: List<ClientProjectInfo>,
    onProjectClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(vertical = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Body2(text = stringResource(id = R.string.my_projects))

        data.forEach { projectInfo ->
            ClientProjectInfoContent(
                projectInfo = projectInfo,
                onProjectClick = onProjectClick
            )
        }
    }
}

/**
 * Карточка проекта клиента
 *
 * @param projectInfo модель с информацией о проекте клиента
 * @param onProjectClick действие по нажатию на карточку проекта
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun ClientProjectInfoContent(
    projectInfo: ClientProjectInfo,
    onProjectClick: (Int) -> Unit
) {
    val executor = stringResource(id = if (projectInfo.isHaveExecutor) R.string.executor_have else R.string.executor_no)
    val price = stringResource(id = R.string.price_value, projectInfo.price)
    val currentStatus = when (projectInfo.status) {
        ProjectStatus.UNKNOWN -> stringResource(id = R.string.status_unknown)
        ProjectStatus.OPEN -> stringResource(id = R.string.status_open)
        ProjectStatus.IN_WORK -> stringResource(id = R.string.status_in_work)
        ProjectStatus.CLOSED -> stringResource(id = R.string.status_closed)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(
                color = PlAntTokens.CardBackground.getThemedColor(),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = { onProjectClick.invoke(projectInfo.id) }),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {
            Column {
                Body2(
                    modifier = Modifier.padding(bottom = 4.dp),
                    text = projectInfo.name
                )
                Body3(text = currentStatus)
                Body3(text = executor)
                Body3(text = price)
            }
        }
    }
}