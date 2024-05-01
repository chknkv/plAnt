package cdr.corecompose.theming

import androidx.compose.ui.graphics.Color

/**
 * Значения цветов для темной темы
 *
 * @author Alexandr Chekunkov
 */
internal fun darkTheme(): Map<PlAntTokens, Color> = mapOf(
    PlAntTokens.Background0 to Graphite2,
    PlAntTokens.Background1 to Graphite1,
    PlAntTokens.ButtonBrand to Blue0,
    PlAntTokens.ButtonDisabled to Gray0,
    PlAntTokens.ButtonWarning to Orange0,
    PlAntTokens.ButtonTransparent to Transparent,
    PlAntTokens.BottomNavigationBackground to Graphite1,
    PlAntTokens.BottomNavigationItemColor to Gray2,
    PlAntTokens.BottomNavigationItemSelectedColor to Blue0,
    PlAntTokens.Brand to Blue0,
    PlAntTokens.ChipSelectableText to Black0,
    PlAntTokens.ChipNotSelectableText to Gray2,
    PlAntTokens.ChipWarningText to Orange1,
    PlAntTokens.ChipNotSelectableBackground to Graphite0,
    PlAntTokens.ChipSelectableBackground to Gray1,
    PlAntTokens.ChipWarningBackground to Orange2,
    PlAntTokens.IconSecondary to White1,
    PlAntTokens.TextPrimary to White0,
    PlAntTokens.TextSecondary to White1,
    PlAntTokens.TextButtonDisabled to Graphite0,
    PlAntTokens.TextButtonWhite to White0,
    PlAntTokens.TextBrand to Blue0,
    PlAntTokens.TextWarning to Orange0,
    PlAntTokens.Transparent to Transparent,
    PlAntTokens.TextFieldBackground to Graphite0,
    PlAntTokens.TextFieldText to Gray2,
    PlAntTokens.TextFieldUnfocusedText to Gray2,
    PlAntTokens.TextFieldUnfocusedLabel to Gray2,
    PlAntTokens.TextFieldWarningBackground to Orange2,
    PlAntTokens.TextFieldWarningText to Orange1,
    PlAntTokens.TextFieldWarningUnfocusedText to Orange1,
    PlAntTokens.TextFieldWarningUnfocusedLabel to Orange1,
    PlAntTokens.Primary to White0,
    PlAntTokens.SnackbarCardContainerPrimary to White0,
    PlAntTokens.SnackbarCardContentPrimary to Black0,
    PlAntTokens.Secondary to White1,
    PlAntTokens.Warning to Orange0
)
