package cdr.corecompose.theming

import androidx.compose.ui.graphics.Color

/**
 * Значения цветов для светлой темы
 *
 * @author Alexandr Chekunkov
 */
internal fun lightTheme(): Map<PlAntTokens, Color> = mapOf(
    PlAntTokens.Background0 to White0,
    PlAntTokens.Background1 to White0,
    PlAntTokens.ButtonBrand to Blue0,
    PlAntTokens.ButtonDisabled to Gray0,
    PlAntTokens.ButtonWarning to Orange0,
    PlAntTokens.ButtonTransparent to Transparent,
    PlAntTokens.BottomNavigationBackground to Gray3,
    PlAntTokens.BottomNavigationItemColor to Graphite0,
    PlAntTokens.BottomNavigationItemSelectedColor to Blue1,
    PlAntTokens.Brand to Blue0,
    PlAntTokens.CardBackground to Gray1,
    PlAntTokens.ChipSelectableText to White0,
    PlAntTokens.ChipNotSelectableText to Graphite0,
    PlAntTokens.ChipWarningText to Orange0,
    PlAntTokens.ChipNotSelectableBackground to Gray1,
    PlAntTokens.ChipSelectableBackground to Graphite0,
    PlAntTokens.ChipWarningBackground to Orange3,
    PlAntTokens.IconPrimary to Black0,
    PlAntTokens.IconSecondary to Black1,
    PlAntTokens.TextPrimary to Black0,
    PlAntTokens.TextSecondary to Black1,
    PlAntTokens.TextButtonDisabled to Black1,
    PlAntTokens.TextButtonWhite to White0,
    PlAntTokens.TextBrand to Blue0,
    PlAntTokens.TextWarning to Orange0,
    PlAntTokens.Transparent to Transparent,
    PlAntTokens.TextFieldBackground to Gray1,
    PlAntTokens.TextFieldText to Black0,
    PlAntTokens.TextFieldUnfocusedText to Graphite0,
    PlAntTokens.TextFieldUnfocusedLabel to Graphite0,
    PlAntTokens.TextFieldWarningBackground to Orange3,
    PlAntTokens.TextFieldWarningText to Orange0,
    PlAntTokens.TextFieldWarningUnfocusedText to Orange0,
    PlAntTokens.TextFieldWarningUnfocusedLabel to Orange0,
    PlAntTokens.Primary to Black0,
    PlAntTokens.SnackbarCardContainerPrimary to Graphite0,
    PlAntTokens.SnackbarCardContentPrimary to White0,
    PlAntTokens.Secondary to Black1,
    PlAntTokens.Warning to Orange0
)
