package cdr.corecompose.appbar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor

/**
 * Навигационная панель сверху экрана
 *
 * @param modifier модифаер
 * @param backgroundColor цвет навигационной панели
 * @param title текст на навигационной панели
 * @param navigationButton навигационная кнопка в начале панели
 * @param navigationButtonClick действие по нажатию навигационной кнопки
 * @param navigationButtonTint цвет навигационной кнопки
 * @param paddingValues отсупы внутри навигационной панели
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun AppBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    title: String? = null,
    navigationButton: AppBarNavigationButtons = AppBarNavigationButtons.None,
    navigationButtonClick: (() -> Unit)? = null,
    navigationButtonTint: Color = PlAntTokens.Primary.getThemedColor(),
    paddingValues: PaddingValues = PaddingValues(vertical = 16.dp, horizontal = 0.dp)
) {
    Row(
        modifier = modifier
            .then(
                Modifier
                    .fillMaxWidth()
                    .height(64.dp)
                    .background(backgroundColor)
                    .padding(paddingValues)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (navigationButton != AppBarNavigationButtons.None) {
            IconButton(
                modifier = Modifier.size(24.dp),
                onClick = navigationButtonClick ?: {}
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = navigationButton.painter),
                    contentDescription = stringResource(id = navigationButton.contentDescription),
                    tint = navigationButtonTint
                )
            }
        }

        val titlePadding = if (navigationButton != AppBarNavigationButtons.None) 16.dp else 0.dp
        if (title != null) {
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = titlePadding),
                maxLines = 1,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    color = PlAntTokens.TextPrimary.getThemedColor(),
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    letterSpacing = 0.sp,
                    fontWeight = FontWeight.Bold
                ),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

/**
 * Навигационные кнопки для AppBar
 *
 * @param painter id иконки на кнопке
 * @param contentDescription id строки описания кнопки
 *
 * @author Alexandr Chekunkov
 */
sealed class AppBarNavigationButtons(
    @DrawableRes val painter: Int,
    @StringRes val contentDescription: Int
) {
    /** Кнопка отсутсвует */
    object None : AppBarNavigationButtons(
        painter = 0,
        contentDescription = 0
    )

    /** Кнопка "Закрыть" - крест */
    object Close : AppBarNavigationButtons(
        painter = cdr.coreresourceslib.R.drawable.ic_close,
        contentDescription = cdr.coreresourceslib.R.string.close
    )

    /** Кнопка "Назад" - стелка */
    object Back : AppBarNavigationButtons(
        painter = cdr.coreresourceslib.R.drawable.ic_back,
        contentDescription = cdr.coreresourceslib.R.string.back
    )
}