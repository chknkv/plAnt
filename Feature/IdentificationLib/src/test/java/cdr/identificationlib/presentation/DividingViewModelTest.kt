package cdr.identificationlib.presentation

import cdr.identificationlib.models.presentation.DividingAction
import cdr.identificationlib.presentation.dividing.DividingViewModel
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

/**
 * Тест для [DividingViewModel]
 *
 * @author Alexandr Chekunkov
 */
internal class DividingViewModelTest {

    private val viewModel = DividingViewModel()

    @Test
    @Disabled
    fun `launch authorization screen`() = runTest {
        val actualActions = mutableListOf<DividingAction>()
        val expectedActions = listOf(DividingAction.LaunchAuthorizationScreen)

        launch(Dispatchers.IO) {
            viewModel.action.toList(actualActions)
        }.also { job ->
            viewModel.launchAuthorizationScreen()
            job.cancel()
        }

        assertThat(actualActions).isEqualTo(expectedActions)
    }

    @Test
    @Disabled
    fun `launch registration screen`() = runTest {
        val actualActions = mutableListOf<DividingAction>()
        val expectedActions = listOf(DividingAction.LaunchRegistrationScreen)

        launch(Dispatchers.IO) {
            viewModel.action.toList(actualActions)
        }.also { job ->
            viewModel.launchRegistrationScreen()
            job.cancel()
        }

        assertThat(actualActions).isEqualTo(expectedActions)
    }
}