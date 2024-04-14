package cdr.identificationlib.presentation.registration

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import cdr.identificationlib.R
import cdr.identificationlib.models.presentation.RegistrationAction
import cdr.coreresourceslib.R as CoreR
import cdr.identificationlib.models.presentation.RegistrationScreen
import cdr.identificationlib.models.presentation.RegistrationState
import cdr.identificationlib.presentation.registration.RegistrationViewModel.Companion.DEVELOPER_ID
import cdr.identificationlib.presentation.registration.RegistrationViewModel.Companion.QA_ID
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
import cdr.corecompose.text.Title2
import cdr.corecompose.textfield.TextFieldCard
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * Контент для экрана регистрации
 *
 * @param viewModel ViewModel для экрана регистрации
 * @param onNavigationPressed действие по нажатию на навигационную кнопку
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun RegistrationContent(
    viewModel: RegistrationViewModel,
    onNavigationPressed: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is RegistrationState.Loading -> LoadingShimmer(viewModel = viewModel)
        is RegistrationState.Screen -> Screen(
            viewModel = viewModel,
            data = currentState.data,
            onNavigationPressed = onNavigationPressed)
    }
}

/**
 * Стандартное состояние экрана, с которым взаимодействует пользователь
 *
 * @param viewModel ViewModel для экрана авторизации
 * @param data UI-модель, содержащая в себе данные на экране
 * @param onNavigationPressed действие по нажатию на навигационную кнопку
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun Screen(
    viewModel: RegistrationViewModel,
    data: RegistrationScreen,
    onNavigationPressed: () -> Unit
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
                navigationButton = AppBarNavigationButtons.Back,
                navigationButtonClick = { viewModel.onNavigationPressed() },
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
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PlAntTokens.Background0.getThemedColor())
                    .weight(1f)
            ) {
                Title2(
                    text = stringResource(id = R.string.enter_information),
                    modifier = Modifier.fillMaxWidth()
                )
                Body3Secondary(
                    text = stringResource(id = R.string.to_registration_enter_new_account_information),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }

            Column {
                TextFieldCard(
                    style = data.login.style,
                    text = data.login.text,
                    label = stringResource(id = CoreR.string.login),
                    onTextChange = viewModel::handleNewLogin,
                    subtitleVisibility = data.login.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.firstPassword.style,
                    text = data.firstPassword.text,
                    label = stringResource(id = CoreR.string.password),
                    onTextChange = viewModel::handleFirstPassword,
                    subtitleVisibility = data.firstPassword.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                    keyboardOptions =  KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                TextFieldCard(
                    style = data.secondPassword.style,
                    text = data.secondPassword.text,
                    label = stringResource(id = CoreR.string.again_password),
                    onTextChange = viewModel::handleSecondPassword,
                    subtitleVisibility = data.secondPassword.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                TextFieldCard(
                    style = data.name.style,
                    text = data.name.text,
                    label = stringResource(id = CoreR.string.name),
                    onTextChange = viewModel::handleName,
                    subtitleVisibility = data.name.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.surname.style,
                    text = data.surname.text,
                    label = stringResource(id = CoreR.string.surname),
                    onTextChange = viewModel::handleSurname,
                    subtitleVisibility = data.surname.subtitleVisibility,
                    subtitleText = stringResource(id = CoreR.string.entered_max_number_of_characters)
                )

                ChipCardGroup(
                    chips = listOf(
                        ChipData(id = DEVELOPER_ID, style = data.roleChips.chipsStyle, title = stringResource(id = CoreR.string.developer)),
                        ChipData(id = QA_ID, style = data.roleChips.chipsStyle, title = stringResource(id = CoreR.string.qa))
                    ),
                    onSelectedChips = viewModel::handleRole
                )

                Blueberry(
                    text = stringResource(id = CoreR.string.next),
                    style = BlueberryStyle.Standard,
                    onClick = { viewModel.signUp() }
                )
            }
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
                viewModel.action.collect { registrationAction ->
                    when (registrationAction) {
                        RegistrationAction.EmptyFields -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = emptyFieldsMessage
                        )

                        RegistrationAction.DifferentPasswords -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = differentPasswordsMessage
                        )

                        RegistrationAction.TinyPassword -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = tinyPasswordMessage
                        )

                        RegistrationAction.EasyPassword -> {
                            showSnackbarCard(
                                snackbarHostState = snackbarHostState,
                                coroutineScope = coroutineScope,
                                message = easyPasswordMessage
                            )
                        }

                        RegistrationAction.BackPressed -> onNavigationPressed.invoke()
                    }
                }
            }
        }
    }
}


/**
 * Отображения AlertDialog с информацией
 *
 * @param viewModel ViewModel для экрана авторизации
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun InfoAlertDialog(
    viewModel: RegistrationViewModel
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
 * @param viewModel ViewModel для экрана авторизации
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun LoadingShimmer(
    viewModel: RegistrationViewModel
) {
    Scaffold(
        modifier = Modifier
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                navigationButton = AppBarNavigationButtons.Back,
                navigationButtonClick = { viewModel.onNavigationPressed() },
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