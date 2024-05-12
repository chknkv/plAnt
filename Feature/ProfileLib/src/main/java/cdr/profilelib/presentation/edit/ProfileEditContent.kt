package cdr.profilelib.presentation.edit

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
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
import cdr.corecompose.chip.chipcard.ChipData
import cdr.corecompose.chip.chipgroup.ChipCardGroup
import cdr.corecompose.progressbar.ProgressBarCircle
import cdr.corecompose.snackbar.SnackbarCard
import cdr.corecompose.snackbar.SnackbarCardData
import cdr.corecompose.snackbar.showSnackbarCard
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.textfield.TextFieldCard
import cdr.corecompose.textfield.TextFieldCardStyles
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.profilelib.models.domain.ClientInfoDomain
import cdr.profilelib.models.presentation.edit.ProfileEditScreen
import cdr.profilelib.models.presentation.edit.ProfileEditState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import cdr.profilelib.R
import cdr.profilelib.di.DaggerProfileComponent
import cdr.profilelib.models.presentation.edit.ProfileEditAction
import cdr.profilelib.models.presentation.edit.RoleChip
import cdr.profilelib.presentation.edit.ProfileEditViewModel.Companion.DEVELOPER_ID
import cdr.profilelib.presentation.edit.ProfileEditViewModel.Companion.QA_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране редактирования профиля клиента
 *
 * @param currentClientInfo текущие данные о клиенте
 * @param onFinish лямбда закрытия экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ProfileEditContent(
    currentClientInfo: ClientInfoDomain,
    onFinish: () -> Unit
) {
    val profileComponent by lazy { DaggerProfileComponent.create() }
    val viewModel = viewModel<ProfileEditViewModel>(factory = profileComponent.getProfileEditViewModelFactory())
    LaunchedEffect(Unit) { viewModel.fetchCurrentProfileInfo(currentClientInfo) }

    val state by viewModel.state.collectAsStateWithLifecycle()
    when (val currentState = state) {
        is ProfileEditState.Loading -> LoadingShimmer()
        is ProfileEditState.Screen -> Screen(
            viewModel = viewModel,
            data = currentState.data,
            onFinish = onFinish
        )
    }
}

/**
 * Стандартное состояние экрана, с которым взаимодействует пользователь
 *
 * @param viewModel ViewModel для экрана редактирования профиля клиента
 * @param data
 * @param onFinish лямбда закрытия экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun Screen(
    viewModel: ProfileEditViewModel,
    data: ProfileEditScreen,
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
                title = stringResource(id = R.string.profile_edit),
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
                    text = stringResource(id = R.string.profile_edit_title)
                )

                Body3Secondary(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    text = stringResource(id = R.string.profile_edit_second_title)
                )

                TextFieldCard(
                    style = TextFieldCardStyles.Standard,
                    text = TextFieldValue(data.username),
                    label = stringResource(id = CoreR.string.login),
                    onTextChange = {},
                    subtitleVisibility = false,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                    enabled = false
                )

                TextFieldCard(
                    style = data.name.style,
                    text = data.name.text,
                    label = stringResource(id = CoreR.string.name),
                    onTextChange = viewModel::handleNewName,
                    subtitleVisibility = data.name.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.surname.style,
                    text = data.surname.text,
                    label = stringResource(id = CoreR.string.surname),
                    onTextChange = viewModel::handleNewSurname,
                    subtitleVisibility = data.surname.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                ChipCardGroup(
                    chips = listOf(
                        ChipData(
                            id = DEVELOPER_ID,
                            style = data.roleChips.chipsStyle,
                            title = stringResource(id = CoreR.string.developer),
                            isSelected = data.roleChips.selectedChipRole == RoleChip.DEVELOPER
                        ),
                        ChipData(
                            id = QA_ID,
                            style = data.roleChips.chipsStyle,
                            title = stringResource(id = CoreR.string.qa),
                            isSelected = data.roleChips.selectedChipRole == RoleChip.QA
                        )
                    ),
                    onSelectedChips = viewModel::handleNewRole
                )

                TextFieldCard(
                    style = data.firstPassword.style,
                    text = data.firstPassword.text,
                    label = stringResource(id = CoreR.string.new_password),
                    onTextChange = viewModel::handleNewFirstPassword,
                    subtitleVisibility = data.firstPassword.subtitleVisibility,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.secondPassword.style,
                    text = data.secondPassword.text,
                    label = stringResource(id = CoreR.string.again_new_password),
                    onTextChange = viewModel::handleNewSecondPassword,
                    subtitleVisibility = data.secondPassword.subtitleVisibility,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )
            }

            Blueberry(
                text = stringResource(id = R.string.edit),
                style = BlueberryStyle.Standard,
                onClick = viewModel::editProfileInfo
            )
        }
    }

    if (data.isShowErrorAlert) InfoAlertDialog(viewModel = viewModel)

    val lifecycleOwner = LocalLifecycleOwner.current
    val emptyFieldsMessage = stringResource(id = CoreR.string.complete_the_steps_above_to_continue)
    val differentPasswordsMessage = stringResource(id = CoreR.string.passwords_must_be_same)
    val tinyPasswordMessage = stringResource(id = CoreR.string.tiny_password)
    val easyPasswordMessage = stringResource(id = CoreR.string.password_must_consist_of_numbers_uppercase_and_lowercase_letters)
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                viewModel.action.collect { profileEditAction ->
                    when (profileEditAction) {
                        ProfileEditAction.EmptyFields -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = emptyFieldsMessage
                        )

                        ProfileEditAction.DifferentPasswords -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = differentPasswordsMessage
                        )

                        ProfileEditAction.TinyPassword -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = tinyPasswordMessage
                        )

                        ProfileEditAction.EasyPassword -> {
                            showSnackbarCard(
                                snackbarHostState = snackbarHostState,
                                coroutineScope = coroutineScope,
                                message = easyPasswordMessage
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
private fun InfoAlertDialog(
    viewModel: ProfileEditViewModel
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
                title = stringResource(id = R.string.profile_edit),
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