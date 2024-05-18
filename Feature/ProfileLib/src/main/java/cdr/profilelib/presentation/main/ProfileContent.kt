package cdr.profilelib.presentation.main

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import cdr.corecompose.text.Body2
import cdr.corecompose.text.Body3
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.text.Title2
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.profilelib.models.presentation.main.ProfileState
import cdr.profilelib.R
import cdr.profilelib.di.DaggerProfileComponent
import cdr.profilelib.models.domain.ClientDomain
import cdr.profilelib.models.domain.ClientInfoDomain
import cdr.profilelib.models.domain.ClientInfoRoleDomain
import cdr.profilelib.models.presentation.main.ProfileAction
import cdr.profilelib.presentation.edit.ProfileEditActivity
import cdr.projectlib.models.domain.ProjectInfoDomain
import cdr.projectlib.presentation.card.ProjectInfoCard
import cdr.projectlib.presentation.info.ProjectInfoActivity
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране профиля клиента
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun ProfileContent() {
    val profileComponent by lazy { DaggerProfileComponent.create() }
    val viewModel = viewModel<ProfileViewModel>(factory = profileComponent.getProfileViewModelFactory())

    var shouldRefresh by remember { mutableStateOf(false) }
    val activityLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { shouldRefresh = true }

    val lifecycleOwner = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    LaunchedEffect(shouldRefresh) {
        viewModel.fetchProfileData()
        shouldRefresh = false
    }
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
            viewModel.action.collect { action -> handleAction(context, action, activityLauncher) }
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
    data: ClientDomain
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
    data: ClientInfoDomain,
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
                text = "${data.username} / ${stringResource(id = if (data.role == ClientInfoRoleDomain.QA) CoreR.string.qa else CoreR.string.developer)}",
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
    data: List<ProjectInfoDomain>,
    onProjectClick: (ProjectInfoDomain) -> Unit
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
                onProjectClick = onProjectClick,
                showAuthor = false
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
 * @param context контекст
 * @param action экшены для экрана профиля клиента
 * @param activityLauncher слушатель на изменение и запуска activity
 *
 * @author Alexandr Chekunkov
 */
private fun handleAction(
    context: Context,
    action: ProfileAction,
    activityLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>
) = when(action) {
    is ProfileAction.LaunchProjectInfoScreen -> context.startActivity(ProjectInfoActivity.newIntent(context, action.projectInfo))
    is ProfileAction.LaunchEditProfile -> activityLauncher.launch(ProfileEditActivity.newIntent(context, action.clientInfo))
}