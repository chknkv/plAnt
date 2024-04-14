package cdr.identificationlib.presentation.dividing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import cdr.identificationlib.models.Navigator
import cdr.identificationlib.models.presentation.DividingAction
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.viewModelCreator
import kotlinx.coroutines.launch

/**
 * [Fragment] для разводящего экрана
 *
 * @author Alexandr Chekunkov
 */
internal class DividingFragment : Fragment() {

    private val viewModel by viewModelCreator<DividingViewModel> { DividingViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.action.collect(::initNavigator)
            }
        }
        return ComposeView(requireContext()).apply {
            setContent {
                PlAntTheme {
                    DividingContent(viewModel = viewModel)
                }
            }
        }
    }

    private fun initNavigator(action: DividingAction) {
        val navigator = requireActivity() as Navigator

        when (action) {
            DividingAction.LaunchAuthorizationScreen -> navigator.launchAuthorizationScreen()
            DividingAction.LaunchRegistrationScreen -> navigator.launchRegistrationScreen()
        }
    }

    companion object {
        const val TAG = "DividingFragment"

        /** Фабричный метод для создания [DividingFragment] */
        fun newInstance(): DividingFragment = DividingFragment()
    }
}