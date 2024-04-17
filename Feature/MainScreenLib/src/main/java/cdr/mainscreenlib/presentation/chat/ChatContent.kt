package cdr.mainscreenlib.presentation.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cdr.corecompose.appbar.AppBar
import cdr.corecompose.appbar.AppBarNavigationButtons
import cdr.corecompose.text.Body3Secondary
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.mainscreenlib.R
import cdr.coreresourceslib.R as CoreR

/**
 * Контент на экране чатов
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ChatContent() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp),
        topBar = {
            AppBar(
                backgroundColor = PlAntTokens.Background0.getThemedColor(),
                title = stringResource(id = R.string.messages),
                navigationButton = AppBarNavigationButtons.None,
                navigationButtonClick = { },
                navigationButtonTint = PlAntTokens.Primary.getThemedColor()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PlAntTokens.Background0.getThemedColor())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.padding(vertical = 16.dp),
                painter = painterResource(id = CoreR.drawable.illustration_256_empty),
                contentDescription = null
            )

            Body3Secondary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.chats_will_appear_here_soon_we_are_working_on_this_functionality),
                textAlign = TextAlign.Center
            )
        }
    }
}