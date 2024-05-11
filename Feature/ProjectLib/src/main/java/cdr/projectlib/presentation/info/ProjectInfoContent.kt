package cdr.projectlib.presentation.info

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdr.corecompose.buttons.blueberry.Blueberry
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.text.Body4
import cdr.corecompose.text.Body4Secondary
import cdr.corecompose.text.Title2
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.projectlib.R
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.models.domain.ProjectStatusDomain
import kotlinx.coroutines.launch

/**
 * Контент со шторкой с информацией о проекте
 *
 * @param onFinishActivity лямбда завершения экрана
 * @param projectInfo модель с информацией о проекте
 *
 * @author Alexandr Chekunkov
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ProjectInfoContent(
    onFinishActivity: () -> Unit,
    projectInfo: ProjectInfoDomain
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val authorTitle = stringResource(id = R.string.author)
    val authorValue = projectInfo.author.ifBlank { stringResource(id = R.string.unknown) }

    val statusTitle = stringResource(id = R.string.status)
    val statusValue = when (projectInfo.status) {
        ProjectStatusDomain.UNKNOWN -> stringResource(id = R.string.status_unknown)
        ProjectStatusDomain.OPEN -> stringResource(id = R.string.status_open)
        ProjectStatusDomain.IN_WORK -> stringResource(id = R.string.status_in_work)
        ProjectStatusDomain.CLOSED -> stringResource(id = R.string.status_closed)
    }

    val executorTitle = stringResource(id = R.string.executor)
    val executorValue = if (projectInfo.executor.isBlank() || !projectInfo.isHaveExecutor) stringResource(id = R.string.executor_no)
        else projectInfo.executor

    val priceTitle = stringResource(id = R.string.price)
    val priceValue = stringResource(id = R.string.price_with_dot, projectInfo.price)

    val descriptionValue = projectInfo.description.ifBlank { stringResource(id = R.string.description_not_find) }

    LaunchedEffect(Unit) { showBottomSheet = true }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                containerColor = PlAntTokens.Background0.getThemedColor(),
                contentColor = PlAntTokens.Background0.getThemedColor(),
                onDismissRequest = {
                    showBottomSheet = false
                    onFinishActivity.invoke()
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 32.dp
                        )
                        .background(PlAntTokens.Background0.getThemedColor())
                        .verticalScroll(rememberScrollState())
                ) {
                    Title2(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        text = projectInfo.name,
                        maxLines = 3
                    )

                    InfoRow(icon = R.drawable.ic_author, title = authorTitle, value = authorValue)
                    InfoRow(icon = R.drawable.ic_status, title = statusTitle, value = statusValue)
                    InfoRow(icon = R.drawable.ic_executor, title = executorTitle, value = executorValue)
                    InfoRow(icon = R.drawable.ic_price, title = priceTitle, value = priceValue)

                    Body4Secondary(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        text = descriptionValue
                    )

                    Blueberry(
                        text = "Понятно",
                        style = BlueberryStyle.Standard,
                        onClick = {
                            scope.launch { sheetState.hide() }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    onFinishActivity.invoke()
                                    showBottomSheet = false
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

/**
 * Ряд с информацией
 *
 * @param icon
 * @param title
 * @param value
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun InfoRow(
    @DrawableRes icon: Int,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = PlAntTokens.IconPrimary.getThemedColor()
        )
        Body4(modifier = Modifier.padding(horizontal = 4.dp), text = title)
        Body4Secondary(text = value)
    }
}