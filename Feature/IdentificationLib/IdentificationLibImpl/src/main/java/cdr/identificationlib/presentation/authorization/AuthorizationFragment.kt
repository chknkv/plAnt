package cdr.identificationlib.presentation.authorization

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

/**
 * [Fragment] для экрана авторизации
 *
 * @author Alexandr Chekunkov
 */
internal class AuthorizationFragment : Fragment() {

    private val identificationComponent by lazy { DaggerIdentificationComponent.create() }
    private val viewModel by viewModelCreator<AuthorizationViewModel> {
        AuthorizationViewModel(
            identificationInteractor = identificationComponent.getIdentificationInteractor()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PlAntTheme {
                    AuthorizationContent(
                        viewModel = viewModel,
                        onNavigationPressed = {
                            (requireActivity() as Navigator).onNavigationPressed()
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val TAG = "AuthorizationFragment"

        /** Фабричный метод для создания [AuthorizationFragment] */
        fun newInstance(): AuthorizationFragment = AuthorizationFragment()
    }
}