package cdr.mainscreenlib.presentation.profile

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.text.Body2
import cdr.corecompose.text.Body3
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.text.Title2
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.coreutilslib.logs.Logger
import cdr.mainscreenlib.models.presentation.ClientInfo
import cdr.mainscreenlib.models.presentation.ClientProjectInfo
import cdr.mainscreenlib.models.presentation.ProfileAction
import cdr.mainscreenlib.models.presentation.ProfileInfo
import cdr.mainscreenlib.models.presentation.ProfileState
import cdr.mainscreenlib.models.presentation.ProjectStatus
import cdr.mainscreenlibimpl.R
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране профиля клиента
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ProfileContent() {
    val viewModel = viewModel<ProfileViewModel>()
    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    LaunchedEffect(Unit) { viewModel.fetchProfileData() }
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            viewModel.action.collect { action -> handleAction(context, action) }
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is ProfileState.Error -> ErrorProfileScreen()
        is ProfileState.Loading -> LoadingShimmer()
        is ProfileState.Successful -> SuccessfulProfileScreen(viewModel, currentState.data)
    }
}

/**
 * Успешное отображение контента на экране профиля клиента
 *
 * @param viewModel ViewModel для экрана профиля клиента
 * @param data UI-модель, содержащая в себе данные о профиле
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun SuccessfulProfileScreen(
    viewModel: ProfileViewModel,
    data: ProfileInfo
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = CoreR.string.profile),
                navigationButton = AppBarNavigationButtons.None,
                navigationButtonClick = { },
                navigationButtonTint = PlAntTokens.Primary.getThemedColor()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(PlAntTokens.Background0.getThemedColor())
        ) {
            ClientInformationContent(
                data = data.clientInfo,
                onEditProfileButtonClick = viewModel::launchEditScreen
            )

            HorizontalDivider(
                modifier = Modifier
                    .height(0.2.dp)
                    .background(PlAntTokens.IconPrimary.getThemedColor())
            )

            ClientProjectsInformationContent(
                data = data.projectInfoList,
                onProjectClick = viewModel::launchInfoAboutProject
            )
        }
    }
}

/**
 * Контент с информацией о клиенте
 *
 * @param data модель с информацией о клиента
 * @param onEditProfileButtonClick действие по нажатию на кнопку редактирования профиля
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun ClientInformationContent(
    data: ClientInfo,
    onEditProfileButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(bottom = 12.dp)
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onEditProfileButtonClick
        ) {
            Icon(
                painter = painterResource(id = CoreR.drawable.ic_edit),
                tint = PlAntTokens.IconPrimary.getThemedColor(),
                contentDescription = stringResource(id = R.string.profile_edit)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(96.dp),
                painter = painterResource(id = CoreR.drawable.ic_profile),
                tint = PlAntTokens.IconPrimary.getThemedColor(),
                contentDescription = stringResource(id = R.string.profile_image)
            )

            Title2(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp),
                text = "${data.firstName} ${data.lastName}",
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            Body3(
                modifier = Modifier.fillMaxWidth(),
                text = "${data.username} / ${data.role}",
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }
}

/**
 * Контент со списком проектов клиента
 *
 * @param data список моделей с информацией о проекте клиента
 * @param onProjectClick действие по нажатию на карточку проекта
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun ClientProjectsInformationContent(
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
            ProjectInfoCard(
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
private fun ProjectInfoCard(
    projectInfo: ClientProjectInfo,
    onProjectClick: (Int) -> Unit
) {
    val executor = stringResource(id = if (projectInfo.isHaveExecutor) R.string.executor_have else R.string.executor_no)
    val price = stringResource(id = R.string.price_value, projectInfo.price)
    val status = when (projectInfo.status) {
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
                title = stringResource(id = CoreR.string.profile),
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
private fun ErrorProfileScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = R.string.profile),
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
 * Обработка экшена (может быть запуск шторки с информацией о проекте или запуск экрана редактирования профиля)
 *
 * @author Alexandr Chekunkov
 */
private fun handleAction(context: Context, action: ProfileAction) = when(action) {
    is ProfileAction.LaunchProjectInfoScreen -> Logger.d("ProfileContent", "launch info ${action.projectId}")
    is ProfileAction.LaunchEditProfile -> Logger.d("ProfileContent", "launch edit profile")
}