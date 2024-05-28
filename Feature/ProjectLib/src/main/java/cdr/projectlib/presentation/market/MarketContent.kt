package cdr.projectlib.presentation.market

import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.projectlib.R
import cdr.projectlib.di.DaggerProjectComponent
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.models.presentation.MarketAction
import cdr.projectlib.models.presentation.MarketInfo
import cdr.projectlib.models.presentation.MarketState
import cdr.projectlib.presentation.add.ProjectAddActivity
import cdr.projectlib.presentation.card.ProjectInfoCard
import cdr.projectlib.presentation.info.ProjectInfoActivity
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране биржы
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun MarketContent() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val projectComponent by lazy { DaggerProjectComponent.factory().create(context) }
    val viewModel = viewModel<MarketViewModel>(factory = projectComponent.getMarketViewModelFactory())

    var shouldRefresh by remember { mutableStateOf(false) }
    val activityLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { shouldRefresh = true }

    LaunchedEffect(shouldRefresh) {
        viewModel.fetchMarketData()
        shouldRefresh = false
    }

    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            viewModel.action.collect { action -> handleAction(context, action, activityLauncher) }
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
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = PlAntTokens.ButtonBrand.getThemedColor(),
                contentColor = PlAntTokens.TextButtonWhite.getThemedColor(),
                onClick = viewModel::launchAddProjectScreen
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_create_project),
                    contentDescription = stringResource(id = R.string.create_new_project)
                )
            }
        }
    ) { paddingValues ->
        MarketProjectInformationContent(
            modifier = Modifier.padding(paddingValues),
            projectInfoList = data.projectInfoList,
            onProjectClick = viewModel::launchInfoAboutProject
        )
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
    projectInfoList: List<ProjectInfoDomain>,
    onProjectClick: (ProjectInfoDomain) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
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
 * @param context контекст
 * @param action экшены для экрана профиля клиента
 * @param activityLauncher слушатель на изменение и запуска activity
 *
 * @author Alexandr Chekunkov
 */
private fun handleAction(
    context: Context,
    action: MarketAction,
    activityLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) = when(action) {
    is MarketAction.LaunchProjectInfoScreen -> context.startActivity(ProjectInfoActivity.newIntent(context, action.projectInfo))
    is MarketAction.LaunchCreateProjectScreen -> activityLauncher.launch(ProjectAddActivity.newIntent(context))
}
