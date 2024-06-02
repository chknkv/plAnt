package cdr.reportlib.presentation.report

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.alert.AlertDialog
import cdr.corecompose.alert.AlertDialogData
import cdr.corecompose.appbar.AppBar
import cdr.corecompose.appbar.AppBarNavigationButtons
import cdr.corecompose.buttons.blueberry.Blueberry
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.snackbar.SnackbarCard
import cdr.corecompose.snackbar.SnackbarCardData
import cdr.corecompose.snackbar.showSnackbarCard
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.textfield.TextFieldCard
import cdr.corecompose.textfield.TextFieldCardStyles
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.coreresourceslib.R as CoreR
import cdr.reportlib.R
import cdr.reportlib.di.DaggerReportComponent
import cdr.reportlib.models.domain.ProjectInfoDomain
import cdr.reportlib.models.presentation.ReportAction
import cdr.reportlib.models.presentation.ReportScreen
import cdr.reportlib.models.presentation.ReportState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Контент на экране создания репорта об найденных ошибках
 *
 * @param onFinishActivity лямбда завершения экрана
 * @param projectInfo модель с информацией о проекте
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ReportContent(
    onFinishActivity: () -> Unit,
    projectInfo: ProjectInfoDomain
) {
    val context = LocalContext.current
    val reportComponent by lazy { DaggerReportComponent.factory().create(context) }
    val viewModel = viewModel<ReportViewModel>(factory = reportComponent.getReportViewModelFactory())

    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is ReportState.Loading -> LoadingShimmer()
        is ReportState.Screen -> Screen(
            viewModel = viewModel,
            data = currentState.data,
            projectInfo = projectInfo,
            onFinishActivity = onFinishActivity
        )
    }
}

@Composable
private fun Screen(
    viewModel: ReportViewModel,
    projectInfo: ProjectInfoDomain,
    data: ReportScreen,
    onFinishActivity: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.add_report),
                navigationButton = AppBarNavigationButtons.Back,
                navigationButtonClick = onFinishActivity,
                navigationButtonTint = PlAntTokens.Primary.getThemedColor()
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                SnackbarCard(
                    snackbarData = snackbarData,
                    data = SnackbarCardData(
                        icon = CoreR.drawable.ic_warning,
                        iconTint = PlAntTokens.Warning.getThemedColor(),
                        closeIcon = true
                    )
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PlAntTokens.Background0.getThemedColor())
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PlAntTokens.Background0.getThemedColor())
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Body3Secondary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 4.dp),
                    text = stringResource(id = R.string.report_description)
                )

                TextFieldCard(
                    style = TextFieldCardStyles.Standard,
                    text = TextFieldValue(projectInfo.name),
                    label = stringResource(id = R.string.project_name),
                    onTextChange = {},
                    subtitleVisibility = false,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                    enabled = false
                )

                TextFieldCard(
                    style = TextFieldCardStyles.Standard,
                    text = TextFieldValue(projectInfo.author),
                    label = stringResource(id = R.string.project_author),
                    onTextChange = {},
                    subtitleVisibility = false,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                    enabled = false
                )

                TextFieldCard(
                    style = data.reportName.style,
                    text = data.reportName.text,
                    label = stringResource(id = R.string.project_name_report),
                    maxLines = 1,
                    onTextChange = viewModel::handleNewReportName,
                    subtitleVisibility = data.reportName.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.report.style,
                    text = data.report.text,
                    label = stringResource(id = R.string.project_description_report),
                    maxLines = 8,
                    onTextChange = viewModel::handleNewReport,
                    subtitleVisibility = data.report.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )
            }

            Blueberry(
                text = stringResource(id = R.string.send),
                style = BlueberryStyle.Standard,
                onClick = { viewModel.sendReport(projectInfo.id, onFinishActivity) }
            )
        }

        if (data.isShowErrorAlert) InfoAlertDialog(viewModel = viewModel)

        val lifecycleOwner = LocalLifecycleOwner.current
        val emptyFieldsMessage = stringResource(id = CoreR.string.complete_the_steps_above_to_continue)
        LaunchedEffect(Unit) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                withContext(Dispatchers.Main.immediate) {
                    viewModel.action.collect { reportAction ->
                        when (reportAction) {
                            ReportAction.EmptyFields -> showSnackbarCard(
                                snackbarHostState = snackbarHostState,
                                coroutineScope = coroutineScope,
                                message = emptyFieldsMessage
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Отображения AlertDialog с информацией
 *
 * @param viewModel ViewModel для экрана создания нового проекта
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun InfoAlertDialog(viewModel: ReportViewModel) {
    AlertDialog(
        data = AlertDialogData(
            title = stringResource(id = CoreR.string.error_has_occurred),
            subtitle = stringResource(id = CoreR.string.check_the_correctness_of_the_entered_data_and_internet),
            image = CoreR.drawable.illustration_256_empty,
            firstButtonText = stringResource(id = CoreR.string.close),
            firstButtonStyle = BlueberryStyle.Negative,
            properties = DialogProperties(dismissOnClickOutside = false)
        ),
        onDismissClick = { viewModel.dismissAlertDialog() },
        onFirstButtonClick = { viewModel.dismissAlertDialog() }
    )
}

/**
 * Шиммер загрузки/обработки данных
 *
 * @author Alexandr Chekunkov
 */
/**
 * Шиммер загрузки/обработки данных
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun LoadingShimmer() {
    Scaffold(
        modifier = Modifier
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.add_report),
                navigationButton = AppBarNavigationButtons.None,
                navigationButtonClick = { },
                navigationButtonTint = PlAntTokens.Primary.getThemedColor()
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PlAntTokens.Background0.getThemedColor()),
            contentAlignment = Alignment.Center
        ) {
            ProgressBarCircle()
        }
    }
}

/**
 * Функция запуска [SnackbarCard].
 *
 * @param snackbarHostState хост для вспывающего окна
 * @param coroutineScope скоуп-короутин
 * @param message сообщение, отображаемое на вспыващем окне
 *
 * @author Alexandr Chekunkov
 */
private fun showSnackbarCard(
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    message: String
) {
    coroutineScope.launch { snackbarHostState.showSnackbarCard(message) }
}