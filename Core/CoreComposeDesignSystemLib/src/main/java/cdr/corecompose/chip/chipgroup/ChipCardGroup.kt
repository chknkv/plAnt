package cdr.corecompose.chip.chipgroup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cdr.corecompose.chip.chipcard.ChipData
import cdr.corecompose.chip.chipcard.ChipCard

/**
 * Набор карточке-чипсов
 *
 * @param modifier модифаер
 * @param isSingleSelect одиночный ли выбор в группе карточек-чипсов
 * @param chips список чипсов для отображения
 * @param onSelectedChips лямбда, возвращающая все выбранные чипсы
 *
 * @author Alexandr Chekunkov
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipCardGroup(
    modifier: Modifier = Modifier,
    isSingleSelect: Boolean = true,
    chips: List<ChipData>,
    onSelectedChips: (List<Int>) -> Unit
) {
    val maxSelected = if (isSingleSelect) SINGLE_MAX_SIZE_OF_SELECTED_CHIPS else Int.MAX_VALUE
    val selectedChipsList = remember { mutableStateListOf<Int>().take(maxSelected).toMutableStateList() }
    chips.forEach { chip -> if (chip.isSelected) selectedChipsList.add(chip.id) }

    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        chips.forEach { chip ->
            ChipCard(
                title = chip.title,
                style = chip.style,
                isSelected = selectedChipsList.contains(chip.id),
                onSelectChanged = { isSelected ->
                    when {
                        isSelected && isSingleSelect -> {
                            selectedChipsList.clear()
                            selectedChipsList.add(chip.id)
                        }

                        !isSelected && isSingleSelect -> {
                            selectedChipsList.clear()
                        }

                        isSelected && !isSingleSelect -> {
                            selectedChipsList.add(chip.id)
                        }

                        else -> {
                            selectedChipsList.remove(chip.id)
                        }
                    }
                    onSelectedChips(selectedChipsList)
                }
            )
        }
    }
}

private const val SINGLE_MAX_SIZE_OF_SELECTED_CHIPS = 1