package cdr.identificationlib.presentation.registration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.viewModelCreator
import cdr.identificationlib.di.DaggerIdentificationComponent
import cdr.identificationlib.models.Navigator
import cdr.mainscreenlib.launcher.MainScreenLauncherImpl

/**
 * [Fragment] для экрана регистрации
 *
 * @author Alexandr Chekunkov
 */
internal class RegistrationFragment : Fragment() {

    private val identificationComponent by lazy { DaggerIdentificationComponent.factory().create(requireContext()) }

    private val viewModel by viewModelCreator<RegistrationViewModel> {
        RegistrationViewModel(identificationInteractor = identificationComponent.getIdentificationInteractor())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PlAntTheme {
                    RegistrationContent(
                        viewModel = viewModel,
                        onNavigationPressed = { (requireActivity() as Navigator).onNavigationPressed() },
                        onLaunchMainScreen = {
                            MainScreenLauncherImpl().launchMainScreen(requireActivity())
                            requireActivity().finish()
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val TAG = "RegistrationFragment"

        /** Фабричный метод для создания [RegistrationFragment] */
        fun newInstance(): RegistrationFragment = RegistrationFragment()
    }
}