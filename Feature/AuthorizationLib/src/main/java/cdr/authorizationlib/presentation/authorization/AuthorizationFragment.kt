package cdr.authorizationlib.presentation.authorization

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import cdr.authorizationlib.data.interactor.IdentificationInteractorImpl
import cdr.authorizationlib.data.mapper.IdentificationMapperImpl
import cdr.authorizationlib.data.repository.IdentificationRepositoryImpl
import cdr.authorizationlib.models.Navigator
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.network.BaseRestClientImpl
import cdr.coreutilslib.utils.viewModelCreator

/**
 * [Fragment] для экрана авторизации
 *
 * @author Alexandr Chekunkov
 */
internal class AuthorizationFragment : Fragment() {

    // TODO: refactor DI
    private val viewModel by viewModelCreator<AuthorizationViewModel> {
        AuthorizationViewModel(authorizationInteractor = IdentificationInteractorImpl(
            identificationRepository = IdentificationRepositoryImpl(
                identificationMapper = IdentificationMapperImpl(
                    retrofit = BaseRestClientImpl().baseRestClient()
                )
            )
        ))
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