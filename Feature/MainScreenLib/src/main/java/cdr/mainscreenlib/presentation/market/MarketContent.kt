package cdr.mainscreenlib.presentation.market

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.appbar.AppBar
import cdr.corecompose.appbar.AppBarNavigationButtons
import cdr.corecompose.buttons.blueberry.Blueberry
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.text.Body2
import cdr.corecompose.text.Body3
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.coreutilslib.logs.Logger
import cdr.mainscreenlib.models.presentation.MarketAction
import cdr.mainscreenlib.models.presentation.MarketInfo
import cdr.mainscreenlib.models.presentation.MarketState
import cdr.mainscreenlib.models.presentation.ProjectInfo
import cdr.mainscreenlib.models.presentation.ProjectMarketStatus
import cdr.mainscreenlib.presentation.market.projectinfo.ProjectInfoActivity
import cdr.mainscreenlibimpl.R
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране биржы
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun MarketContent() {
    val viewModel = viewModel<MarketViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    LaunchedEffect(Unit) { viewModel.fetchMarketData() }
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            viewModel.action.collect { action -> handleAction(context, action) }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    when(val currentState = state) {
        MarketState.Error -> ErrorMarketScreen()
        MarketState.Loading -> LoadingShimmer()
        is MarketState.Successful -> SuccessfulMarketScreen(viewModel, currentState.data)
    }
}

/**
 * Успешное отображение контента на экране биржы
 *
 * @param viewModel ViewModel для экраны биржы
 * @param data UI-модель, содержащая в себе данные на экране биржы
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun SuccessfulMarketScreen(
    viewModel: MarketViewModel,
    data: MarketInfo
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.market),
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
                .padding(paddingValues)
        ) {
            MarketProjectInformationContent(
                modifier = Modifier.weight(1f),
                projectInfoList = data.projectInfoList,
                onProjectClick = viewModel::launchInfoAboutProject
            )

            Blueberry(
                text = stringResource(id = R.string.create_new_project),
                style = BlueberryStyle.Standard,
                onClick = viewModel::launchAddProjectScreen,
                verticalPadding = 8.dp
            )
        }
    }
}

/**
 * Картока с информацией о проекте на бирже
 *
 * @param projectInfoList список моделей с информацией о проекте
 * @param onProjectClick действие по нажатию на карточку
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun MarketProjectInformationContent(
    modifier: Modifier,
    projectInfoList: List<ProjectInfo>,
    onProjectClick: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(PlAntTokens.Background0.getThemedColor())
            .verticalScroll(rememberScrollState())
    ) {
        projectInfoList.forEach { projectInfo ->
            ProjectInfoCard(
                projectInfo = projectInfo,
                onProjectClick = onProjectClick
            )
        }
    }
}

/**
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun ProjectInfoCard(
    projectInfo: ProjectInfo,
    onProjectClick: (Int) -> Unit
) {
    val executor = stringResource(id = if (projectInfo.isHaveExecutor) R.string.executor_have else R.string.executor_no)
    val price = stringResource(id = R.string.price_value, projectInfo.price)
    val author = stringResource(id = R.string.author_value, projectInfo.author)
    val status = when (projectInfo.status) {
        ProjectMarketStatus.UNKNOWN -> stringResource(id = R.string.status_unknown)
        ProjectMarketStatus.OPEN -> stringResource(id = R.string.status_open)
        ProjectMarketStatus.IN_WORK -> stringResource(id = R.string.status_in_work)
        ProjectMarketStatus.CLOSED -> stringResource(id = R.string.status_closed)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp)
            .background(
                color = PlAntTokens.CardBackground.getThemedColor(),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(onClick = { onProjectClick.invoke(projectInfo.id) })
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
                Body3(text = author)
                Body3(text = status)
                Body3(text = executor)
                Body3(text = price)
            }
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
                title = stringResource(id = CoreR.string.market),
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
 * Экран ошибки при загрзуке данных
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun ErrorMarketScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.market),
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
 * Обработка экшена (может быть запуск шторки с информацией о экране или экран создания нового проекта)
 *
 * @author Alexandr Chekunkov
 */
private fun handleAction(context: Context, action: MarketAction) = when(action) {
    is MarketAction.LaunchProjectInfoScreen -> context.startActivity(ProjectInfoActivity.newIntent(context))

//    is MarketAction.LaunchProjectInfoScreen -> Logger.d("MarketContent", "launch info ${action.projectId}")
    is MarketAction.LaunchCreateProjectScreen -> Logger.d("MarketContent", "launch create")
}
