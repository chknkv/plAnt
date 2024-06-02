package cdr.reportlib.presentation.info.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import cdr.corecompose.buttons.blueberry.Blueberry
import cdr.corecompose.buttons.blueberry.BlueberryStyle
import cdr.corecompose.buttons.dual.horizontal.DualBlueberryHorizontal
import cdr.corecompose.text.Body4
import cdr.corecompose.text.Title2
import cdr.corecompose.theming.PlAntTokens
import cdr.corecompose.theming.getThemedColor
import cdr.reportlib.di.DaggerReportComponent
import cdr.reportlib.models.domain.ReportInfoDomain
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import cdr.reportlib.R

/**
 * Контент со шторкой с информацией о баге-репорте
 *
 * @param onFinishActivity лямбда завершения экрана
 * @param reportInfo модель с информацией о проекте
 *
 * @author Alexandr Chekunkov
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun DetailsReportInfoContent(
    onFinishActivity: () -> Unit,
    reportInfo: ReportInfoDomain
) {
    val context = LocalContext.current
    val projectComponent by lazy { DaggerReportComponent.factory().create(context) }
    viewModel<DetailsReportInfoViewModel>(factory = projectComponent.getDetailsReportInfoViewModelFactory())

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) { showBottomSheet = true }
    Box(modifier = Modifier.fillMaxSize()) {
        if (showBottomSheet) {
            ModalBottomSheet(
                sheetState = sheetState,
                containerColor = PlAntTokens.Background2.getThemedColor(),
                contentColor = PlAntTokens.Background2.getThemedColor(),
                onDismissRequest = {
                    showBottomSheet = false
                    onFinishActivity.invoke()
                }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 32.dp
                        )
                        .background(PlAntTokens.Background2.getThemedColor())
                        .verticalScroll(rememberScrollState())
                ) {
                    Title2(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 8.dp),
                        text = reportInfo.reportName,
                        maxLines = 3
                    )

                    if (reportInfo.isPaid)
                        PaidContent(reportInfo = reportInfo) {
                            closeBottomSheet(
                                scope = scope,
                                sheetState = sheetState,
                                onFinishActivity = onFinishActivity,
                                closeBottomSheet = { showBottomSheet = false }
                            )
                        }
                    else
                        NotPaidContent {
                            closeBottomSheet(
                                scope = scope,
                                sheetState = sheetState,
                                onFinishActivity = onFinishActivity,
                                closeBottomSheet = { showBottomSheet = false }
                            )
                        }
                }
            }
        }
    }
}

/**
 * Контент на шторке при неоплаченном баге-репорте
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun PaidContent(
    reportInfo: ReportInfoDomain,
    closeBottomSheet: () -> Unit
) {
    Column {
        Body4(text = stringResource(id = R.string.report_paid))

        Blueberry(
            text = stringResource(id = R.string.ok),
            style = BlueberryStyle.Standard,
            onClick = closeBottomSheet
        )
    }
}

/**
 * Контент на шторке при оплаченном баге-репорте
 *
 * @author Alexandr Chekunkov
 */
@Composable
private fun NotPaidContent(closeBottomSheet: () -> Unit) {
    Column {
        Body4(text = stringResource(id = R.string.report_not_paid))

        DualBlueberryHorizontal(
            firstButtonText = stringResource(id = R.string.pay),
            secondButtonText = stringResource(id = R.string.ok),
            firstButtonClick = {  },
            secondButtonClick = { closeBottomSheet.invoke() },
            firstButtonStyle = BlueberryStyle.Standard,
            secondButtonStyle = BlueberryStyle.Negative
        )
    }
}

/**
 * Закрытие шторки с информации
 *
 * @author Alexandr Chekunkov
 */
@OptIn(ExperimentalMaterial3Api::class)
private fun closeBottomSheet(
    scope: CoroutineScope,
    sheetState: SheetState,
    onFinishActivity: () -> Unit,
    closeBottomSheet: () -> Unit
) {
    scope.launch { sheetState.hide() }.invokeOnCompletion {
        if (!sheetState.isVisible) {
            onFinishActivity.invoke()
            closeBottomSheet.invoke()
        }
    }
}