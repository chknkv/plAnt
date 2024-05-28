package cdr.projectlib.presentation.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
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
import cdr.corecompose.chip.chipcard.ChipData
import cdr.corecompose.chip.chipgroup.ChipCardGroup
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.snackbar.SnackbarCard
import cdr.corecompose.snackbar.SnackbarCardData
import cdr.corecompose.snackbar.showSnackbarCard
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.textfield.TextFieldCard
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.projectlib.models.presentation.ProjectAddScreen
import cdr.projectlib.models.presentation.ProjectAddState
import cdr.projectlib.R
import cdr.projectlib.di.DaggerProjectComponent
import cdr.projectlib.models.presentation.NewProjectOsChip
import cdr.projectlib.models.presentation.ProjectAddAction
import cdr.projectlib.presentation.add.ProjectAddViewModel.Companion.ANDROID_ID
import cdr.projectlib.presentation.add.ProjectAddViewModel.Companion.IOS_ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране создания нового профиля
 *
 * @param onFinish лямбда закрытия экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ProjectAddContent(
    onFinish: () -> Unit
) {
    val context = LocalContext.current
    val projectComponent by lazy { DaggerProjectComponent.factory().create(context) }
    val viewModel = viewModel<ProjectAddViewModel>(factory = projectComponent.getProjectAddViewModelFactory())

    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is ProjectAddState.Loading -> LoadingShimmer()
        is ProjectAddState.Screen -> Screen(
            viewModel = viewModel,
            data = currentState.data,
            onFinish = onFinish
        )
    }
}

/**
 * Стандартное состояние экрана, с которым взаимодействует пользователь
 *
 * @param viewModel ViewModel для экрана создания нового проекта
 * @param data UI-модель, содержащая в себе данные на экране
 * @param onFinish лямбда закрытия экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun Screen(
    viewModel: ProjectAddViewModel,
    data: ProjectAddScreen,
    onFinish: () -> Unit
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
                title = stringResource(id = R.string.create_new_project),
                navigationButton = AppBarNavigationButtons.Back,
                navigationButtonClick = onFinish,
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
                    text = stringResource(id = R.string.create_new_project_description)
                )

                Body3Secondary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    text = stringResource(id = R.string.create_new_project_second_description)
                )

                TextFieldCard(
                    style = data.name.style,
                    text = data.name.text,
                    label = stringResource(id = R.string.name_title),
                    onTextChange = viewModel::handleNewName,
                    subtitleVisibility = data.name.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.price.style,
                    text = data.price.text,
                    label = stringResource(id = R.string.price_title),
                    onTextChange = viewModel::handleNewPrice,
                    subtitleVisibility = data.price.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                TextFieldCard(
                    style = data.description.style,
                    text = data.description.text,
                    label = stringResource(id = R.string.description_title),
                    onTextChange = viewModel::handleNewDescription,
                    subtitleVisibility = data.description.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.link.style,
                    text = data.link.text,
                    label = stringResource(id = R.string.resources),
                    onTextChange = viewModel::handleNewLink,
                    subtitleVisibility = data.link.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                ChipCardGroup(
                    chips = listOf(
                        ChipData(
                            id = ANDROID_ID,
                            style = data.osChips.chipsStyle,
                            title = stringResource(id = CoreR.string.android),
                            isSelected = data.osChips.selectedChipOs == NewProjectOsChip.ANDROID
                        ),
                        ChipData(
                            id = IOS_ID,
                            style = data.osChips.chipsStyle,
                            title = stringResource(id = CoreR.string.iOS),
                            isSelected = data.osChips.selectedChipOs == NewProjectOsChip.IOS
                        )
                    ),
                    onSelectedChips = viewModel::handleOs
                )
            }

            Blueberry(
                text = stringResource(id = R.string.create_new_project),
                style = BlueberryStyle.Standard,
                onClick = { viewModel.createNewProject(onFinish) }
            )
        }

        if (data.isShowErrorAlert) InfoAlertDialog(viewModel = viewModel)

        val lifecycleOwner = LocalLifecycleOwner.current
        val snackbarMessageEmptyField = stringResource(id = CoreR.string.complete_the_steps_above_to_continue)
        val snackbarMessageNotLink = stringResource(id = R.string.link_is_incorrect)
        LaunchedEffect(Unit) {
            lifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.action.collect { action ->
                    when (action) {
                        is ProjectAddAction.EmptyFields -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = snackbarMessageEmptyField
                        )

                        is ProjectAddAction.NotLink -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = snackbarMessageNotLink
                        )
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
private fun InfoAlertDialog(
    viewModel: ProjectAddViewModel
) {
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
@Composable
private fun LoadingShimmer() {
    Scaffold(
        modifier = Modifier
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = R.string.create_new_project),
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