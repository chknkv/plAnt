package cdr.reportlib.models.presentation

import androidx.compose.ui.text.input.TextFieldValue
import cdr.corecompose.textfield.TextFieldCardStyles

/**
 * Состояния экрана создания баг-репорта
 *
 * @author Alexandr Chekunkov
 */
internal sealed interface ReportState {

    /** Загрузка */
    object Loading : ReportState

    /**
     * Стандартное состояние экрана, с которым взаимодействует пользователь
     *
     * @property data UI-модель, содержащая в себе данные на экране
     */
    @JvmInline
    value class Screen(val data: ReportScreen = ReportScreen()) : ReportState
}

/**
 * UI-модель, содержащая в себе данные на экране
 *
 * @property reportName название бага-репорта
 * @property report поле ввода сообщения о баге-репорте
 * @property isShowErrorAlert нужно ли показывать AlertDialog с ошибкой
 *
 * @author Alexandr Chekunkov
 */
internal data class ReportScreen(
    val reportName: ReportField = ReportField(),
    val report: ReportField = ReportField(),
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
internal data class ReportField(
    val text: TextFieldValue = TextFieldValue(),
    val subtitleVisibility: Boolean = false,
    val style: TextFieldCardStyles = TextFieldCardStyles.Standard
)
