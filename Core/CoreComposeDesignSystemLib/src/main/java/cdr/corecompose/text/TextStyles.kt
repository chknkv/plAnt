package cdr.corecompose.text

import android.webkit.URLUtil
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import cdr.corecompose.theming.getThemedColor
import cdr.corecompose.theming.PlAntTokens

/**
 * Headline1
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline1(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline2
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline2(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline3
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline3(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)


/**
 * Title1
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title1(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Title2
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title2(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body1
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body1(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body2
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body2(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body3
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body3(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body4
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body4(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline1Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline1Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline2Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline2Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline3Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline3Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)


/**
 * Title1Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title1Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Title2Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title2Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body1Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body1Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body2Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body2Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body3Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body3Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextSecondary.getThemedColor(),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body4Secondary
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body4Secondary(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextPrimary.getThemedColor(),
        fontSize = 12.sp,
        lineHeight = 12.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body4SecondaryLink
 *
 * @param textLink текст-ссылка
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body4SecondaryLink(
    textLink: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) {
    val uriHandler = LocalUriHandler.current

    Text(
        text = textLink,
        modifier = modifier.clickable { if (URLUtil.isValidUrl(textLink)) uriHandler.openUri(textLink) },
        maxLines = maxLines,
        textAlign = textAlign,
        style = TextStyle(
            color = PlAntTokens.LinkBackground.getThemedColor(),
            fontSize = 12.sp,
            lineHeight = 12.sp,
            letterSpacing = 0.em
        ),
        overflow = TextOverflow.Ellipsis
    )
}

/**
 * Headline1Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline1Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline2Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline2Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline3Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline3Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)


/**
 * Title1Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title1Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Title2Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title2Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body1Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body1Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body2Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body2Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body3Brand
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body3Brand(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextBrand.getThemedColor(),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)


/**
 * Headline1Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline1Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 30.sp,
        lineHeight = 36.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline2Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline2Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Headline3Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Headline3Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 24.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)


/**
 * Title1Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title1Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Title2Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Title2Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body1Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body1Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body2Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body2Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 15.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.em,
        fontWeight = FontWeight.Bold
    ),
    overflow = TextOverflow.Ellipsis
)

/**
 * Body3Warning
 *
 * @param text текст
 * @param modifier модифаер
 * @param maxLines максимальное количество строк текста
 * @param textAlign выравнивание текста
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun Body3Warning(
    text: String,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    textAlign: TextAlign? = null
) = Text(
    text = text,
    modifier = modifier,
    maxLines = maxLines,
    textAlign = textAlign,
    style = TextStyle(
        color = PlAntTokens.TextWarning.getThemedColor(),
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.em
    ),
    overflow = TextOverflow.Ellipsis
)