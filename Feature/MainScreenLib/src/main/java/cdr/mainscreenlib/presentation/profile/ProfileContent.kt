package cdr.mainscreenlib.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.appbar.AppBar
import cdr.corecompose.appbar.AppBarNavigationButtons
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.mainscreenlib.models.presentation.ProfileInfo
import cdr.mainscreenlib.models.presentation.ProfileState
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

    LaunchedEffect(Unit) { viewModel.fetchProfileData() }

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