package cdr.mainscreenlib.presentation.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import cdr.corecompose.theming.PlAntTheme
import cdr.coreutilslib.utils.viewModelCreator

/**
 * [Fragment] для экрана чата
 *
 * @author Alexandr Chekunkov
 */
internal class ChatFragment : Fragment() {

    private val viewModel by viewModelCreator<ChatViewModel> { ChatViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                PlAntTheme {
                    ChatContent()
                }
            }
        }
    }

    companion object {
        const val TAG = "ChatFragment"

        /** Фабричный метод для создания [ChatFragment] */
        fun newInstance(): ChatFragment = ChatFragment()
    }
}