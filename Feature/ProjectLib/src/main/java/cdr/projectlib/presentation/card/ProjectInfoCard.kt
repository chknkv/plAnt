package cdr.projectlib.presentation.card

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cdr.corecompose.text.Body2
import cdr.corecompose.text.Body4
import cdr.corecompose.text.Body4Secondary
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.projectlib.R
import cdr.projectlib.models.domain.ProjectInfo
import cdr.projectlib.models.domain.ProjectStatus

/**
 * Карточка с информацией о проекте
 *
 * @param projectInfo модель с информацией о проекте клиента
 * @param onProjectClick действие по нажатию на карточку проекта
 * @param showAuthor надо ли показывать автора на карточке проекта
 *
 * @author Alexandr Chekunkov
 */
@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ProjectInfoCard(
    projectInfo: ProjectInfo,
    onProjectClick: (ProjectInfo) -> Unit,
    showAuthor: Boolean = true
) {
    val authorTitle = stringResource(id = R.string.author)
    val authorValue = projectInfo.author.ifBlank { stringResource(id = R.string.unknown) }

    val statusTitle = stringResource(id = R.string.status)
    val statusValue = when (projectInfo.status) {
        ProjectStatus.UNKNOWN -> stringResource(id = R.string.status_unknown)
        ProjectStatus.OPEN -> stringResource(id = R.string.status_open)
        ProjectStatus.IN_WORK -> stringResource(id = R.string.status_in_work)
        ProjectStatus.CLOSED -> stringResource(id = R.string.status_closed)
    }

    val priceTitle = stringResource(id = R.string.price)
    val priceValue = stringResource(id = R.string.price_with_dot, projectInfo.price)

    val executorTitle = stringResource(id = R.string.executor)
    val executorValue = stringResource(id = if (projectInfo.isHaveExecutor) R.string.executor_have else R.string.executor_have)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(
                color = PlAntTokens.CardBackground.getThemedColor(),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null,
                onClick = { onProjectClick.invoke(projectInfo) }
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 12.dp
                )
        ) {
            Column {
                Body2(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = 4.dp
                        ),
                    text = projectInfo.name,
                    maxLines = 1
                )

                if (showAuthor) InfoRow(icon = R.drawable.ic_author, title = authorTitle, value = authorValue)
                InfoRow(icon = R.drawable.ic_status, title = statusTitle, value = statusValue)
                InfoRow(icon = R.drawable.ic_executor, title = executorTitle, value = executorValue)
                InfoRow(icon = R.drawable.ic_price, title = priceTitle, value = priceValue)
            }
        }
    }
}

/**
 * Ряд с информацией
 *
 * @param icon
 * @param title
 * @param value
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun InfoRow(
    @DrawableRes icon: Int,
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 1.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(12.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = PlAntTokens.IconPrimary.getThemedColor()
        )
        Body4(modifier = Modifier.padding(horizontal = 4.dp), text = title)
        Body4Secondary(text = value)
    }
}