package cdr.corecompose.navigation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

/**
 * Навигационная панель
 *
 * @param modifier модифаер
 * @param backgroundColor цвет навигационной панели
 * @param itemColor цвет элементов
 * @param itemSelectedColor цвет выбранного элемента
 * @param items список элементов, которые используются в навигационной панели
 *
 * @author Alexandr Chekunkov
 */
@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    itemColor: Color,
    itemSelectedColor: Color,
    items: List<BottomNavigationBarItem>
) {
    val selectedItemId = rememberSaveable { mutableIntStateOf(1) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(backgroundColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items.forEach { item ->
            Column(
                modifier = Modifier
                    .padding(top = 4.dp)
                    .weight(1f)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null,
                        onClick = {
                            selectedItemId.intValue = item.id
                            item.onClick.invoke()
                        }
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = item.icon),
                    tint = if (item.id == selectedItemId.intValue) itemSelectedColor else itemColor,
                    contentDescription = item.iconDescription
                )

                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = item.title,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        color = if (item.id == selectedItemId.intValue) itemSelectedColor else itemColor,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        letterSpacing = 0.em
                    ),
                    overflow = TextOverflow.Ellipsis
                )
            }

        }
    }
}

/**
 * Элемент, который используется в навигационной панели
 *
 * @param id уникальный номер элемента в навигационной панеле (отсчет начинать с единицы)
 * @param icon иконка элемента в навигационной панеле
 * @param iconDescription описание для иконки элемента в навигационной панеле
 * @param title текст элемента в навигационной панеле
 * @param onClick действие по нажатию на элемент в навигационной панеле
 *
 * @author Alexandr Chekunkov
 */
data class BottomNavigationBarItem(
    val id: Int,
    @DrawableRes val icon: Int,
    val iconDescription: String? = null,
    val title: String,
    val onClick: (() -> Unit)
)