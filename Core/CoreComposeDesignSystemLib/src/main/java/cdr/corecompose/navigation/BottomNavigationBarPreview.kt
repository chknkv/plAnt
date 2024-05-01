package cdr.corecompose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.PreviewPlantTheme
import cdr.corecompose.theming.getThemedColor
import cdr.coreresourceslib.R as CoreR

/**
 * Превью для [BottomNavigationBar] (светлая тема)
 *
 * @author Alexandr Chekunkov
 */
@Preview
@Composable
internal fun BottomNavigationBarPreviewLightTheme() {
    PreviewPlantTheme(darkTheme = false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PlAntTokens.Background0.getThemedColor())
        ) {
            BottomNavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    ),
                backgroundColor = PlAntTokens.BottomNavigationBackground.getThemedColor(),
                itemSelectedColor = PlAntTokens.BottomNavigationItemSelectedColor.getThemedColor(),
                itemColor = PlAntTokens.BottomNavigationItemColor.getThemedColor(),
                items = ITEMS
            )
        }
    }
}

/**
 * Превью для [BottomNavigationBar] (темная тема)
 *
 * @author Alexandr Chekunkov
 */
@Preview
@Composable
internal fun BottomNavigationBarPreviewDarkTheme() {
    PreviewPlantTheme(darkTheme = true) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PlAntTokens.Background0.getThemedColor())
        ) {
            BottomNavigationBar(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp
                        )
                    ),
                backgroundColor = PlAntTokens.BottomNavigationBackground.getThemedColor(),
                itemSelectedColor = PlAntTokens.BottomNavigationItemSelectedColor.getThemedColor(),
                itemColor = PlAntTokens.BottomNavigationItemColor.getThemedColor(),
                items = ITEMS
            )
        }
    }
}

private val ITEMS = listOf(
    BottomNavigationBarItem(
        id = 1,
        icon = CoreR.drawable.ic_market,
        title = "Биржа",
        onClick = { }
    ),
    BottomNavigationBarItem(
        id = 2,
        icon = CoreR.drawable.ic_chat,
        title = "Сообщения",
        onClick = { }
    ),
    BottomNavigationBarItem(
        id = 3,
        icon = CoreR.drawable.ic_profile,
        title = "Профиль",
        onClick = { }
    )
)