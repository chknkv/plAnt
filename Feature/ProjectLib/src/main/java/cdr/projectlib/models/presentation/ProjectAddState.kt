package cdr.projectlib.models.presentation

import androidx.compose.ui.text.input.TextFieldValue
import cdr.corecompose.textfield.TextFieldCardStyles

/**
 * Состояния экрана добавления проектов
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ProjectAddState {

    /** Загрузка */
    object Loading : ProjectAddState

    /**
     * Стандартное состояние экрана, с которым взаимодействует пользователь
     *
     * @property data UI-модель, содержащая в себе данные на экране
     */
    @JvmInline
    value class Screen(val data: ProjectAddScreen = ProjectAddScreen()) : ProjectAddState
}

/**
 * UI-модель, содержащая в себе данные на экране
 *
 * @param name название нового проекта
 * @param description описание нового проекта
 * @param price цена нвоого проекта
 * @param link ссылка на ресурс проекта
 * @param isShowErrorAlert нужно ли показывать AlertDialog с ошибкой
 *
 * @author Alexandr Chekunkov
 */
internal data class ProjectAddScreen(
    val name: NewProjectField = NewProjectField(),
    val price: NewProjectField = NewProjectField(),
    val description: NewProjectField = NewProjectField(),
    val link: NewProjectField = NewProjectField(),
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
internal data class NewProjectField(
    val text: TextFieldValue = TextFieldValue(),
    val subtitleVisibility: Boolean = false,
    val style: TextFieldCardStyles = TextFieldCardStyles.Standard
)