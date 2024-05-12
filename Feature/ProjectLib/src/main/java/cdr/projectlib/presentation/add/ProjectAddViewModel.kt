package cdr.projectlib.presentation.add

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.corecompose.textfield.TextFieldCardStyles
import cdr.coreutilslib.logs.Logger
import cdr.projectlib.models.domain.NewProjectDomain
import cdr.projectlib.models.presentation.ProjectAddScreen
import cdr.projectlib.models.presentation.ProjectAddState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] для экрана создания нового проекта
 *
 * @author Alexandr Chekunkov
 */
internal class ProjectAddViewModel : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)

        currentData?.let { currentData ->
            _state.value = ProjectAddState.Screen(data = currentData.copy(isShowErrorAlert = true))
        }
    }

    /** Текущее данные, отображаемые на экране */
    private var currentData: ProjectAddScreen? = null

    /** Текущее состояние экрана */
    val state: StateFlow<ProjectAddState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<ProjectAddState>(ProjectAddState.Screen())

    /** Действие для показа snackbar на экране */
    val action: SharedFlow<Unit> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<Unit>()

    /** Создание нового проекта */
    fun createNewProject() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val currentState = _state.value
            if (currentState is ProjectAddState.Screen) {
                currentData = currentState.data

                currentData?.let { currentData ->
                    if (checkIsNotBlank(currentData)) {
                        _state.value = ProjectAddState.Loading

                        val newProjectDomain = createNewProjectDomain(currentData)
                        Logger.d(TAG, "--->>> newProjectDomain: $newProjectDomain")

                        delay(1500)
                        throw IllegalArgumentException()
                    }
                }
            }
        }
    }

    /** Проверка на пустые поля */
    private suspend fun checkIsNotBlank(currentData: ProjectAddScreen): Boolean {
        val blankName = currentData.name.text.text.isBlank()
        val blankPrice = currentData.price.text.text.isBlank()
        val blankDescription = currentData.description.text.text.isBlank()
        val blankLink = currentData.link.text.text.isBlank()

        return if (blankName || blankPrice || blankDescription || blankLink) {
            _state.value = ProjectAddState.Screen(
                data = currentData.copy(
                    name = currentData.name.copy(
                        style = if (blankName) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    ),
                    price = currentData.price.copy(
                        style = if (blankPrice) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    ),
                    description = currentData.description.copy(
                        style = if (blankDescription) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    ),
                    link = currentData.link.copy(
                        style = if (blankLink) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    )
                )
            )
            _action.emit(Unit)

            false
        } else true
    }

    /** Обработка нового названия проекта с UI */
    fun handleNewName(newProjectName: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProjectAddState.Screen) {
            val currentData = currentState.data

            _state.value = ProjectAddState.Screen(
                data = currentData.copy(
                    name = currentData.name.copy(
                        text = if (newProjectName.text.length <= MAX_CHARACTERS_NAME) newProjectName else currentData.name.text,
                        subtitleVisibility = newProjectName.text.length >= MAX_CHARACTERS_NAME,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    /** Обработка новой цена проекта с UI */
    fun handleNewPrice(newPrice: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProjectAddState.Screen) {
            val currentData = currentState.data

            _state.value = ProjectAddState.Screen(
                data = currentData.copy(
                    price = currentData.price.copy(
                        text = if (newPrice.text.length <= MAX_CHARACTERS_PRICE) newPrice else currentData.price.text,
                        subtitleVisibility = newPrice.text.length >= MAX_CHARACTERS_PRICE,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    /** Обработка нового описания проекта с UI */
    fun handleNewDescription(newDescription: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProjectAddState.Screen) {
            val currentData = currentState.data

            _state.value = ProjectAddState.Screen(
                data = currentData.copy(
                    description = currentData.description.copy(
                        text = if (newDescription.text.length <= MAX_CHARACTERS_DESCRIPTION_LINK) newDescription else currentData.description.text,
                        subtitleVisibility = newDescription.text.length >= MAX_CHARACTERS_DESCRIPTION_LINK,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    /** Обработка новой ссылки на ресурс проекта с UI */
    fun handleNewLink(newLink: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProjectAddState.Screen) {
            val currentData = currentState.data

            _state.value = ProjectAddState.Screen(
                data = currentData.copy(
                    link = currentData.link.copy(
                        text = if (newLink.text.length <= MAX_CHARACTERS_DESCRIPTION_LINK) newLink else currentData.link.text,
                        subtitleVisibility = newLink.text.length >= MAX_CHARACTERS_DESCRIPTION_LINK,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    /** Сокрытие AlertDialog с UI */
    fun dismissAlertDialog() {
        val currentState = _state.value
        if (currentState is ProjectAddState.Screen) {
            val currentData = currentState.data
            _state.value = ProjectAddState.Screen(
                data = currentData.copy(
                    isShowErrorAlert = false
                )
            )
        }
    }

    /** Создание domain-модели для нового проекта */
    private fun createNewProjectDomain(currentData: ProjectAddScreen): NewProjectDomain = NewProjectDomain(
        name = currentData.name.text.text,
        price = currentData.price.text.text.toInt(),
        description = currentData.description.text.text,
        link = currentData.link.text.text
    )

    companion object {
        private const val TAG = "ProjectAddViewModel"
        private const val MAX_CHARACTERS_NAME = 25
        private const val MAX_CHARACTERS_PRICE = 8
        private const val MAX_CHARACTERS_DESCRIPTION_LINK = 255
    }
}