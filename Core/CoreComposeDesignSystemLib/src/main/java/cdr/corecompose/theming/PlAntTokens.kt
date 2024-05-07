package cdr.corecompose.theming

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Токены для темы приложения
 *
 * @author Alexandr Chekunkov
 */
enum class PlAntTokens {
    Background0,
    Background1,
    ButtonBrand,
    ButtonDisabled,
    ButtonWarning,
    ButtonTransparent,
    BottomNavigationBackground,
    BottomNavigationItemColor,
    BottomNavigationItemSelectedColor,
    CardBackground,
    ChipNotSelectableText,
    ChipSelectableText,
    ChipWarningText,
    ChipNotSelectableBackground,
    ChipSelectableBackground,
    ChipWarningBackground,
    Brand,
    IconPrimary,
    IconSecondary,
    TextPrimary,
    TextSecondary,
    TextBrand,
    TextWarning,
    TextButtonDisabled,
    TextButtonWhite,
    Transparent,
    TextFieldBackground,
    TextFieldText,
    TextFieldUnfocusedText,
    TextFieldUnfocusedLabel,
    TextFieldWarningBackground,
    TextFieldWarningText,
    TextFieldWarningUnfocusedText,
    TextFieldWarningUnfocusedLabel,
    Primary,
    SnackbarCardContainerPrimary,
    SnackbarCardContentPrimary,
    Secondary,
    Warning
}

/**
 * Получить цвет, соответствующую текущей теме
 */
@Composable
@ReadOnlyComposable
fun PlAntTokens.getThemedColor(): Color = PlAntTheme.tokens.getValue(this)

/**
 * Токены цветов
 */
internal val LocalTokens: ProvidableCompositionLocal<Map<PlAntTokens, Color>> =
    staticCompositionLocalOf { lightTheme() }
