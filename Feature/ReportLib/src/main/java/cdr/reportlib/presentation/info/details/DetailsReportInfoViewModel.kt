package cdr.reportlib.presentation.info.details

import androidx.lifecycle.ViewModel
import cdr.reportlib.data.interactor.ReportInteractor

/**
 * ViewModel для экрана со шторкой с информацией о баге-репорте
 *
 * @param reportInteractor рнтерактор для функционала модуля баг-репортов
 *
 * @author Alexandr Chekunkov
 */
internal class DetailsReportInfoViewModel(private val reportInteractor: ReportInteractor) : ViewModel()