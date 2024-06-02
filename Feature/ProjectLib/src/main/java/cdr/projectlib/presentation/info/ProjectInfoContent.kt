package cdr.projectlib.presentation.info

import android.content.Context
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
import androidx.compose.material3.SheetState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.buttons.blueberry.Blueberry
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.buttons.dual.horizontal.DualBlueberryHorizontal
import cdr.corecompose.text.Body4
import cdr.corecompose.text.Body4Secondary
import cdr.corecompose.text.Body4SecondaryLink
import cdr.corecompose.text.Title2
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.projectlib.R
import cdr.projectlib.di.DaggerProjectComponent
import cdr.projectlib.models.domain.ClientInfoDomain
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.models.domain.ProjectOperationSystemDomain
import cdr.projectlib.models.domain.ProjectStatusDomain
import cdr.reportlib.presentation.info.ReportInfoActivity
import cdr.reportlib.presentation.report.ReportActivity
import kotlinx.coroutines.CoroutineScope
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
    val context = LocalContext.current
    val projectComponent by lazy { DaggerProjectComponent.factory().create(context) }
    val viewModel = viewModel<ProjectInfoViewModel>(factory = projectComponent.getProjectInfoViewModelFactory())

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { viewModel.fetchInfoAboutClient() }
    val clientInfo = viewModel.clientInfo.collectAsStateWithLifecycle().value

    val authorTitle = stringResource(id = R.string.author)
    val authorValue = projectInfo.author.ifBlank { stringResource(id = R.string.unknown) }

    val statusTitle = stringResource(id = R.string.status)
    val statusValue = when (projectInfo.status) {
        ProjectStatusDomain.UNKNOWN -> stringResource(id = R.string.status_unknown)
        ProjectStatusDomain.OPEN -> stringResource(id = R.string.status_open)
        ProjectStatusDomain.IN_WORK -> stringResource(id = R.string.status_in_work)
        ProjectStatusDomain.CLOSED -> stringResource(id = R.string.status_closed)
    }

    val ocTitle = stringResource(id = R.string.operation_system)
    val ocValue = stringResource(id = if (projectInfo.applicationInfo?.operationSystem == ProjectOperationSystemDomain.IOS) R.string.iOS else R.string.android)

    val linkTitle = stringResource(id = R.string.link)
    val linkValue = projectInfo.applicationInfo?.url ?: stringResource(id = R.string.link_not_found)

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
                containerColor = PlAntTokens.Background2.getThemedColor(),
                contentColor = PlAntTokens.Background2.getThemedColor(),
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
                        .background(PlAntTokens.Background2.getThemedColor())
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
                    InfoRow(icon = R.drawable.ic_price, title = priceTitle, value = priceValue)
                    InfoRow(icon = R.drawable.ic_os, title = ocTitle, value = ocValue)
                    InfoRow(icon = R.drawable.ic_link, title = linkTitle, value = linkValue, isLink = true)

                    Body4Secondary(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp),
                        text = descriptionValue
                    )

                    BottomButtons(
                        context = context,
                        clientInfo = clientInfo,
                        projectInfo = projectInfo,
                        scope = scope,
                        sheetState = sheetState,
                        onFinishActivity = onFinishActivity,
                        closeBottomSheet = { showBottomSheet = false}
                    )
                }
            }
        }
    }
}

/**
 * Ряд с информацией
 *
 * @param icon иконка в ряду
 * @param title заголовок в ряду
 * @param value значение в ряду
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun InfoRow(
    @DrawableRes icon: Int,
    title: String,
    value: String,
    isLink: Boolean = false
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

        if (isLink) {
            Body4SecondaryLink(textLink = value, maxLines = 1)
        } else {
            Body4Secondary(text = value, maxLines = 1)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BottomButtons(
    context: Context,
    clientInfo: ClientInfoDomain,
    projectInfo: ProjectInfoDomain,
    scope: CoroutineScope,
    sheetState: SheetState,
    onFinishActivity: () -> Unit,
    closeBottomSheet: () -> Unit
) {
    val smallProjectInfoDomain = cdr.reportlib.models.domain.ProjectInfoDomain(
        id = projectInfo.id,
        name = projectInfo.name,
        author = projectInfo.author
    )

    when {
        clientInfo.role == ProjectInfoViewModel.ROLE_DEVELOPER && clientInfo.username == projectInfo.author -> DualBlueberryHorizontal(
            firstButtonText = stringResource(id = R.string.errors),
            secondButtonText = stringResource(id = R.string.ok),
            firstButtonClick = {
                context.startActivity(ReportInfoActivity.newIntent(context, smallProjectInfoDomain))
                closeBottomSheet(scope, sheetState, onFinishActivity, closeBottomSheet)
            },
            secondButtonClick = {
                closeBottomSheet(scope, sheetState, onFinishActivity, closeBottomSheet)
            },
            firstButtonStyle = BlueberryStyle.Negative,
            secondButtonStyle = BlueberryStyle.Standard
        )

        clientInfo.role == ProjectInfoViewModel.ROLE_DEVELOPER || projectInfo.status != ProjectStatusDomain.OPEN -> Blueberry(
            text = stringResource(id = R.string.ok),
            style = BlueberryStyle.Standard,
            onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        onFinishActivity.invoke()
                        closeBottomSheet.invoke()
                    }
                }
            }
        )

        clientInfo.role == ProjectInfoViewModel.ROLE_QA -> {
            DualBlueberryHorizontal(
                firstButtonText = stringResource(id = R.string.report),
                secondButtonText = stringResource(id = R.string.close),
                firstButtonClick = {
                    context.startActivity(ReportActivity.newIntent(context, smallProjectInfoDomain))
                    closeBottomSheet(scope, sheetState, onFinishActivity, closeBottomSheet)
                },
                secondButtonClick = {
                    closeBottomSheet(scope, sheetState, onFinishActivity, closeBottomSheet)
                },
                firstButtonStyle = BlueberryStyle.Negative,
                secondButtonStyle = BlueberryStyle.Standard
            )
        }
    }
}


/**
 * Закрытие шторки с информации
 *
 * @author Alexandr Chekunkov
 */
@OptIn(ExperimentalMaterial3Api::class)
private fun closeBottomSheet(
    scope: CoroutineScope,
    sheetState: SheetState,
    onFinishActivity: () -> Unit,
    closeBottomSheet: () -> Unit
) {
    scope.launch { sheetState.hide() }.invokeOnCompletion {
        if (!sheetState.isVisible) {
            onFinishActivity.invoke()
            closeBottomSheet.invoke()
        }
    }
}