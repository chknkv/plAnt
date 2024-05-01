package cdr.mainscreenlib.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.navigation.BottomNavigationBar
import cdr.corecompose.navigation.BottomNavigationBarItem
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.coreresourceslib.R
import cdr.mainscreenlib.models.presentation.Screen
import cdr.mainscreenlib.presentation.chat.ChatContent
import cdr.mainscreenlib.presentation.market.MarketContent
import cdr.mainscreenlib.presentation.profile.ProfileContent
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на главном экране модуля.
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun PrimaryContent() {
    val viewModel = viewModel<PrimaryViewModel>()
    val screen by viewModel.screen.collectAsStateWithLifecycle()

    val items = listOf(
        BottomNavigationBarItem(
            id = 1,
            icon = R.drawable.ic_market,
            title = stringResource(id = CoreR.string.market),
            onClick = { viewModel.changeScreen(Screen.MARKET) }
        ),
        BottomNavigationBarItem(
            id = 2,
            icon = R.drawable.ic_chat,
            title = stringResource(id = CoreR.string.messages),
            onClick = { viewModel.changeScreen(Screen.CHAT) }
        ),
        BottomNavigationBarItem(
            id = 3,
            icon = R.drawable.ic_profile,
            title = stringResource(id = CoreR.string.profile),
            onClick = { viewModel.changeScreen(Screen.PROFILE) }
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (screen) {
                Screen.MARKET -> MarketContent()
                Screen.CHAT -> ChatContent()
                Screen.PROFILE -> ProfileContent()
            }
        }

        BottomNavigationBar(
            modifier = Modifier
                .clip(
                    shape = RoundedCornerShape(
                        topStart = 22.dp,
                        topEnd = 22.dp
                    )
                ),
            backgroundColor = PlAntTokens.BottomNavigationBackground.getThemedColor(),
            itemColor = PlAntTokens.BottomNavigationItemColor.getThemedColor(),
            itemSelectedColor = PlAntTokens.BottomNavigationItemSelectedColor.getThemedColor(),
            items = items
        )
    }
}