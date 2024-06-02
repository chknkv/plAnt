package cdr.reportlib.presentation.info

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.appbar.AppBar
import cdr.corecompose.appbar.AppBarNavigationButtons
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.buttons.dual.horizontal.DualBlueberryHorizontal
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.snackbar.SnackbarCard
import cdr.corecompose.snackbar.SnackbarCardData
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.textfield.TextFieldCard
import cdr.corecompose.textfield.TextFieldCardStyles
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.coreresourceslib.R as CoreR
import cdr.reportlib.R
import cdr.reportlib.di.DaggerReportComponent
import cdr.reportlib.models.domain.ProjectInfoDomain
import cdr.reportlib.models.domain.ReportInfoDomain
import cdr.reportlib.models.presentation.ReportInfoState
import cdr.reportlib.presentation.info.details.DetailsReportInfoActivity

/**
 * Контент на экране с информацией об ошибках
 *
 * @param onFinishActivity лямбда завершения экрана
 * @param projectInfo модель с информацией о проекте
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ReportInfoContent(
    onFinishActivity: () -> Unit,
    projectInfo: ProjectInfoDomain
) {
    val context = LocalContext.current
    val reportComponent by lazy { DaggerReportComponent.factory().create(context) }
    val viewModel = viewModel<ReportInfoViewModel>(factory = reportComponent.getReportInfoViewModelFactory())

    LaunchedEffect(Unit) { viewModel.fetchProjectReport(projectInfo.id) }

    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is ReportInfoState.Loading -> LoadingShimmer()
        is ReportInfoState.Error -> ErrorReportInfoScreen()
        is ReportInfoState.Screen -> Screen(
            viewModel = viewModel,
            projectInfo = projectInfo,
            allReports = currentState.allReports,
            onFinishActivity = onFinishActivity
        )
    }
}

/**
 * Успешно загруженный экран
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun Screen(
    viewModel: ReportInfoViewModel,
    projectInfo: ProjectInfoDomain,
    allReports: List<ReportInfoDomain>,
    onFinishActivity: () -> Unit
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.find_problems),
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
                    text = stringResource(id = R.string.report_info_description)
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

                HorizontalDivider(
                    modifier = Modifier
                        .padding(vertical = 5.dp, horizontal = 12.dp)
                        .height(0.1.dp)
                        .background(PlAntTokens.IconPrimary.getThemedColor())
                )

                allReports.forEach { report ->
                    TextFieldCard(
                        modifier = Modifier.clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null,
                            onClick = { context.startActivity(DetailsReportInfoActivity.newIntent(context, report))  }
                        ),
                        style = TextFieldCardStyles.Warning,
                        text = TextFieldValue(report.reportName),
                        label = stringResource(id = R.string.report_name),
                        onTextChange = {},
                        subtitleVisibility = false,
                        subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                        enabled = false
                    )
                }
            }

            DualBlueberryHorizontal(
                firstButtonText = stringResource(id = R.string.close_project),
                secondButtonText = stringResource(id = R.string.ok),
                firstButtonClick = { viewModel.closeProject(projectInfo.id, onFinishActivity) },
                secondButtonClick = onFinishActivity,
                firstButtonStyle = BlueberryStyle.Negative,
                secondButtonStyle = BlueberryStyle.Standard
            )
        }
    }
}

/**
 * Экран ошибки при загрзуке данных
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun ErrorReportInfoScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.find_problems),
                navigationButton = AppBarNavigationButtons.None,
                navigationButtonClick = { },
                navigationButtonTint = PlAntTokens.Primary.getThemedColor()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PlAntTokens.Background0.getThemedColor())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = CoreR.drawable.illustration_256_empty),
                contentDescription = null
            )

            Body3Secondary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.exception_happened),
                textAlign = TextAlign.Center
            )
        }
    }
}

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
                title = stringResource(id = CoreR.string.find_problems),
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