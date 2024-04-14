package cdr.identificationlib.presentation.dividing

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdr.identificationlib.R
import cdr.coreresourceslib.R as CoreR
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.buttons.dual.vertical.DualBlueberryVertical
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor

/**
 * Контент на разводящем экране
 *
 * @param viewModel ViewModel для разводящего экрана
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun DividingContent(
    viewModel: DividingViewModel
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(horizontal = 16.dp)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(PlAntTokens.Background0.getThemedColor())
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.weight(1f),
                painter = painterResource(id = CoreR.drawable.illustration_256_empty),
                contentDescription = null
            )

            DualBlueberryVertical(
                firstButtonText = stringResource(id = R.string.sign_in),
                firstButtonStyle = BlueberryStyle.Standard,
                firstButtonClick = { viewModel.launchAuthorizationScreen() },
                secondButtonText = stringResource(id = R.string.create_account),
                secondButtonStyle = BlueberryStyle.Transparent,
                secondButtonClick = { viewModel.launchRegistrationScreen() }
            )
        }
    }
}