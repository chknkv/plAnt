package cdr.profilelib.models.presentation.edit

import androidx.compose.ui.text.input.TextFieldValue
import cdr.corecompose.chip.chipcard.ChipCardStyle
import cdr.corecompose.textfield.TextFieldCardStyles

/**
 * Состояния экрана редактирования профиля
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProfileEditState {

    /** Загрузка */
    object Loading : ProfileEditState

    /**
     * Стандартное состояние экрана, с которым взаимодействует пользователь
     *
     * @property data UI-модель, содержащая в себе данные на экране
     */
    @JvmInline
    value class Screen(val data: ProfileEditScreen = ProfileEditScreen()) : ProfileEditState
}

/**
 * UI-модель, содержащая в себе данные на экране
 *
 * @param username текущий логин клиента
 * @param name новое имя клиента
 * @param surname новая фамилимя клиента
 * @param roleChips новая роль клиента
 * @param firstPassword новый пароль клиента
 * @param secondPassword новый пароль клиента (используется для подтверждения пароля)
 * @param isShowErrorAlert нужно ли показывать AlertDialog с ошибкой
 *
 * @author Alexandr Chekunkov
 */
internal data class ProfileEditScreen(
    val username: String = "",
    val name: EditProfileField = EditProfileField(),
    val surname: EditProfileField = EditProfileField(),
    val roleChips: EditProfileChip = EditProfileChip(),
    val firstPassword: EditProfileField = EditProfileField(),
    val secondPassword: EditProfileField = EditProfileField(),
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
internal data class EditProfileField(
    val text: TextFieldValue = TextFieldValue(),
    val subtitleVisibility: Boolean = false,
    val style: TextFieldCardStyles = TextFieldCardStyles.Standard
)

/**
 * UI-модель, содержащая в себе данные чипсов
 *
 * @property selectedChipRole выбранная пользователем роль (разработчик или тестировщик)
 * @property chipsStyle стиль для чипсов
 *
 * @author Alexandr Chekunkov
 */
internal data class EditProfileChip(
    val selectedChipRole: RoleChip? = null,
    val chipsStyle: ChipCardStyle = ChipCardStyle.Standard,
)

/**
 * Роль, выбранная пользователем при регистрации
 *
 * @author Alexandr Chekunkov
 */
internal enum class RoleChip {
    /** Разработчик */
    DEVELOPER,

    /** Тестировщик */
    QA
}