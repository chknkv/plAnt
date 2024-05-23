package cdr.identificationlib.models.presentation

import androidx.compose.ui.text.input.TextFieldValue
import cdr.corecompose.textfield.TextFieldCardStyles

/**
 * Состояние экрана авторизации
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface AuthorizationState {

    /** Загрузка */
    object Loading : AuthorizationState

    /**
     * Стандартное состояние экрана, с которым взаимодействует пользователь
     *
     * @property data UI-модель, содержащая в себе данные на экране
     */
    @JvmInline
    value class Screen(val data: AuthorizationScreen = AuthorizationScreen()) : AuthorizationState
}

/**
 * UI-модель, содержащая в себе данные на экране
 *
 * @property login логин пользователя
 * @property password пароль пользователя
 * @property isShowErrorAlert нужно ли показывать AlertDialog с ошибкой
 *
 * @author Alexandr Chekunkov
 */
internal data class AuthorizationScreen(
    val login: AuthorizationField = AuthorizationField(),
    val password: AuthorizationField = AuthorizationField(),
    val isShowErrorAlert: Boolean = false
)

/**
 * UI-модель, содержащая в себе данные поля ввода текста
 *
 * @property text текст поля ввода
 * @property subtitleVisibility видимость подзаголовка поля ввода
 * @property style стиль поля ввода
 *
 * @author Alexandr Chekunkov
 */
internal data class AuthorizationField(
    val text: TextFieldValue = TextFieldValue(),
    val subtitleVisibility: Boolean = false,
    val style: TextFieldCardStyles = TextFieldCardStyles.Standard
)

/**
 * Экшены для экрана авторизации
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface AuthorizationAction {

    /** Запуск главного экрана после авторизации */
    object LaunchMainScreen : AuthorizationAction

    /** Нажатие на кнопку навигации */
    object BackPressed : AuthorizationAction

    /** Отображение вспывающего окна с сообщением о незаполненных полях */
    object EmptyFields : AuthorizationAction
}