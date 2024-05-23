package cdr.identificationlib.presentation.authorization

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import cdr.identificationlibimpl.R
import cdr.identificationlib.models.presentation.AuthorizationAction
import cdr.identificationlib.models.presentation.AuthorizationScreen
import cdr.identificationlib.models.presentation.AuthorizationState
import cdr.corecompose.alert.AlertDialog
import cdr.corecompose.alert.AlertDialogData
import cdr.coreresourceslib.R as CoreR
import cdr.corecompose.appbar.AppBar
import cdr.corecompose.appbar.AppBarNavigationButtons
import cdr.corecompose.buttons.blueberry.Blueberry
import cdr.corecompose.buttons.blueberry.BlueberryStyle
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
 * Контент на экране авторизации
 *
 * @param viewModel ViewModel для экрана авторизации
 * @param onNavigationPressed действие по нажатию на навигационную кнопку
 * @param onLaunchMainScreen запуск главного экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun AuthorizationContent(
    viewModel: AuthorizationViewModel,
    onNavigationPressed: () -> Unit,
    onLaunchMainScreen: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    when (val currentState = state) {
        is AuthorizationState.Loading -> LoadingShimmer(viewModel = viewModel)
        is AuthorizationState.Screen -> Screen(
            viewModel = viewModel,
            data = currentState.data,
            onNavigationPressed = onNavigationPressed,
            onLaunchMainScreen = onLaunchMainScreen
        )
    }
}

/**
 * Стандартное состояние экрана, с которым взаимодействует пользователь
 *
 * @param viewModel ViewModel для экрана авторизации
 * @param data UI-модель, содержащая в себе данные на экране
 * @param onLaunchMainScreen запуск главного экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun Screen(
    viewModel: AuthorizationViewModel,
    data: AuthorizationScreen,
    onNavigationPressed: () -> Unit,
    onLaunchMainScreen: () -> Unit
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
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Bottom,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(PlAntTokens.Background0.getThemedColor())
                    .weight(1f)
            ) {
                Title2(
                    text = stringResource(id = R.string.enter_login_and_password),
                    modifier = Modifier.fillMaxWidth()
                )
                Body3Secondary(
                    text = stringResource(id = R.string.to_authorize_enter_your_account_information),
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
                    subtitleText = stringResource(id = cdr.coreresourceslib.R.string.entered_max_number_of_characters)
                )

                TextFieldCard(
                    style = data.password.style,
                    text = data.password.text,
                    label = stringResource(id = CoreR.string.password),
                    onTextChange = viewModel::handleNewPassword,
                    subtitleVisibility = data.password.subtitleVisibility,
                    subtitleText = stringResource(id = cdr.coreresourceslib.R.string.entered_max_number_of_characters),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Blueberry(
                    text = stringResource(id = CoreR.string.next),
                    style = BlueberryStyle.Standard,
                    onClick = { viewModel.signIn() }
                )
            }
        }
    }
    
    if (data.isShowErrorAlert) InfoAlertDialog(viewModel = viewModel)

    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarMessage = stringResource(id = CoreR.string.complete_the_steps_above_to_continue)
    LaunchedEffect(Unit) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                viewModel.action.collect { authorizationAction ->
                    when (authorizationAction) {
                        AuthorizationAction.EmptyFields -> showSnackbarCard(
                            snackbarHostState = snackbarHostState,
                            coroutineScope = coroutineScope,
                            message = snackbarMessage
                        )

                        AuthorizationAction.BackPressed -> onNavigationPressed.invoke()

                        AuthorizationAction.LaunchMainScreen -> onLaunchMainScreen.invoke()
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
    viewModel: AuthorizationViewModel
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
    viewModel: AuthorizationViewModel
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