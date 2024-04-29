package cdr.mainscreenlib.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.viewModelCreator

/**
 * [Fragment] для экрана профиля клиента
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileFragment : Fragment() {

    private val viewModel by viewModelCreator<ProfileViewModel> { ProfileViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PlAntTheme {
                    ProfileContent(viewModel = viewModel)
                }
            }
        }
    }

    companion object {

        const val TAG = "ProfileFragment"

        /** Фабричный метод для создания [ProfileFragment] */
        fun newInstance(): ProfileFragment = ProfileFragment()
    }
}