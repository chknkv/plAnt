package cdr.mainscreenlib.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cdr.corecompose.text.Body3
import cdr.corecompose.text.Title2
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.mainscreenlib.models.presentation.ClientInfo
import cdr.mainscreenlibimpl.R
import cdr.coreresourceslib.R as CoreR

/**
 * Контент с информацией о клиенте
 *
 * @param data модель с информацией о клиента
 * @param onEditProfileButtonClick действие по нажатию на кнопку редактирования профиля
 *
 * @author Alexandr Chekunkov
 */
@Composable
internal fun ClientInformationContent(
    data: ClientInfo,
    onEditProfileButtonClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(PlAntTokens.Background0.getThemedColor())
            .padding(bottom = 12.dp)
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onEditProfileButtonClick
        ) {
            Icon(
                painter = painterResource(id = CoreR.drawable.ic_edit),
                tint = PlAntTokens.IconPrimary.getThemedColor(),
                contentDescription = stringResource(id = R.string.profile_edit)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(96.dp),
                painter = painterResource(id = CoreR.drawable.ic_profile),
                tint = PlAntTokens.IconPrimary.getThemedColor(),
                contentDescription = stringResource(id = R.string.profile_image)
            )

            Title2(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp),
                text = "${data.firstName} ${data.lastName}",
                maxLines = 1,
                textAlign = TextAlign.Center
            )

            Body3(
                modifier = Modifier.fillMaxWidth(),
                text = "${data.username} / ${data.role}",
                maxLines = 1,
                textAlign = TextAlign.Center
            )
        }
    }
}