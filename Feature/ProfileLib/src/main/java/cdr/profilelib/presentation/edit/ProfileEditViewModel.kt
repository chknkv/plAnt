package cdr.profilelib.presentation.edit

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cdr.corecompose.chip.chipcard.ChipCardStyle
import cdr.corecompose.textfield.TextFieldCardStyles
import cdr.coreutilslib.logs.Logger
import cdr.profilelib.data.interactor.ProfileInteractor
import cdr.profilelib.models.domain.ClientInfoDomain
import cdr.profilelib.models.domain.ClientInfoRoleDomain
import cdr.profilelib.models.domain.NewClientInfoDomain
import cdr.profilelib.models.domain.NewClientRole
import cdr.profilelib.models.presentation.edit.EditProfileChip
import cdr.profilelib.models.presentation.edit.EditProfileField
import cdr.profilelib.models.presentation.edit.ProfileEditAction
import cdr.profilelib.models.presentation.edit.ProfileEditScreen
import cdr.profilelib.models.presentation.edit.ProfileEditState
import cdr.profilelib.models.presentation.edit.RoleChip
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] для экрана редактирования профиля клиента
 *
 * @param profileInteractor интерактор для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal class ProfileEditViewModel(
    private val profileInteractor: ProfileInteractor
) : ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Logger.e(TAG, "Ошибка доступа к удаленному сервису", exception)

        currentData?.let { currentData ->
            _state.value = ProfileEditState.Screen(data = currentData.copy(isShowErrorAlert = true))
        }
    }

    /** Текущее состояние экрана */
    val state: StateFlow<ProfileEditState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow<ProfileEditState>(ProfileEditState.Loading)

    /** Действие для показа snackbar на экране */
    val action: SharedFlow<ProfileEditAction> get() = _action.asSharedFlow()
    private val _action = MutableSharedFlow<ProfileEditAction>()

    /** Текущее данные, отображаемые на экране */
    private var currentData: ProfileEditScreen? = null

    fun fetchCurrentProfileInfo(currentClientInfo: ClientInfoDomain) {
        _state.value = ProfileEditState.Screen(
            data = ProfileEditScreen(
                username = currentClientInfo.username,
                name = EditProfileField(
                    text = TextFieldValue(text = currentClientInfo.firstName)
                ),
                surname = EditProfileField(
                    text = TextFieldValue(text = currentClientInfo.lastName)
                ),
                roleChips = EditProfileChip(
                    selectedChipRole = if (currentClientInfo.role == ClientInfoRoleDomain.DEVELOPER) RoleChip.DEVELOPER else RoleChip.QA
                )
            )
        )
    }

    /** Редактирование профиля */
    fun editProfileInfo(onFinish: () -> Unit) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val currentState = _state.value
            if (currentState is ProfileEditState.Screen) {
                currentData = currentState.data

                currentData?.let { currentData ->
                    if (checkIsNotBlank(currentData)) {
                        if (needChangePassword(currentData)) {
                            if (checkIsSamePasswords(currentData) &&
                                checkIsNotTinyPassword(currentData) &&
                                checkIsNotEasyPassword(currentData)
                            ) {
                                sendNewProfileData(currentData, onFinish)
                            }
                        } else {
                            sendNewProfileData(currentData, onFinish)
                        }
                    }
                }
            }
        }
    }

    /** Отправка данных на удаленный сервис */
    private suspend fun sendNewProfileData(
        currentData: ProfileEditScreen,
        onFinish: () -> Unit
    ) {
        _state.value = ProfileEditState.Loading

        val newClientInfoDomain = createNewClientInfoDomain(currentData)
        profileInteractor.editClientInfo(newClientInfoDomain)
        onFinish.invoke()
    }

    /** Проверка на пустые поля */
    private suspend fun checkIsNotBlank(currentData: ProfileEditScreen): Boolean {
        val blankName = currentData.name.text.text.isBlank()
        val blankSurname = currentData.surname.text.text.isBlank()
        val blankRole = currentData.roleChips.selectedChipRole == null

        return if (blankName || blankSurname || blankRole) {
            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    name = currentData.name.copy(
                        style = if (blankName) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    ),
                    surname = currentData.surname.copy(
                        style = if (blankSurname) TextFieldCardStyles.Warning else TextFieldCardStyles.Standard
                    ),
                    roleChips = currentData.roleChips.copy(
                        chipsStyle = if (blankRole) ChipCardStyle.Warning else ChipCardStyle.Standard
                    )
                )
            )
            _action.emit(ProfileEditAction.EmptyFields)

            false
        } else true
    }

    private fun needChangePassword(currentData: ProfileEditScreen): Boolean =
        (currentData.firstPassword.text.text.isNotBlank() || currentData.secondPassword.text.text.isNotBlank())

    /** Проверка на равность введенных паролей */
    private suspend fun checkIsSamePasswords(currentData: ProfileEditScreen): Boolean =
        if (currentData.firstPassword.text.text != currentData.secondPassword.text.text) {
            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    firstPassword = currentData.firstPassword.copy(style = TextFieldCardStyles.Warning),
                    secondPassword = currentData.secondPassword.copy(style = TextFieldCardStyles.Warning)
                )
            )
            _action.emit(ProfileEditAction.DifferentPasswords)

            false
        } else true

    /** Проверка на длину пароля */
    private suspend fun checkIsNotTinyPassword(currentData: ProfileEditScreen): Boolean =
        if (currentData.firstPassword.text.text.length < MIN_PASSWORD_SIZE) {
            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    firstPassword = currentData.firstPassword.copy(style = TextFieldCardStyles.Warning),
                    secondPassword = currentData.secondPassword.copy(style = TextFieldCardStyles.Warning)
                )
            )
            _action.emit(ProfileEditAction.TinyPassword)

            false
        } else true

    /** Проверка на сложность пароля */
    private suspend fun checkIsNotEasyPassword(currentData: ProfileEditScreen): Boolean =
        if (!currentData.firstPassword.text.text.matches(Regex(PASSWORD_REGEX))) {
            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    firstPassword = currentData.firstPassword.copy(style = TextFieldCardStyles.Warning),
                    secondPassword = currentData.secondPassword.copy(style = TextFieldCardStyles.Warning)
                )
            )
            _action.emit(ProfileEditAction.EasyPassword)

            false
        } else true

    fun handleNewName(newName: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProfileEditState.Screen) {
            val currentData = currentState.data

            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    name = currentData.name.copy(
                        text = if (newName.text.length <= MAX_CHARACTERS) newName else currentData.name.text,
                        subtitleVisibility = newName.text.length >= MAX_CHARACTERS,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    fun handleNewSurname(newSurname: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProfileEditState.Screen) {
            val currentData = currentState.data

            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    surname = currentData.surname.copy(
                        text = if (newSurname.text.length <= MAX_CHARACTERS) newSurname else currentData.surname.text,
                        subtitleVisibility = newSurname.text.length >= MAX_CHARACTERS,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    fun handleNewRole(list: List<Int>) {
        val currentState = _state.value
        if (currentState is ProfileEditState.Screen) {
            val currentData = currentState.data

            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    roleChips = currentData.roleChips.copy(
                        chipsStyle = ChipCardStyle.Standard,
                        selectedChipRole = when {
                            list.isEmpty() -> null
                            list.first() == DEVELOPER_ID -> RoleChip.DEVELOPER
                            list.first() == QA_ID -> RoleChip.QA
                            else -> null
                        }
                    )
                )
            )
        }
    }

    fun handleNewFirstPassword(newFirstPassword: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProfileEditState.Screen) {
            val currentData = currentState.data

            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    firstPassword = currentData.firstPassword.copy(
                        text = if (newFirstPassword.text.length <= MAX_CHARACTERS) newFirstPassword else currentData.firstPassword.text,
                        subtitleVisibility = newFirstPassword.text.length >= MAX_CHARACTERS,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    fun handleNewSecondPassword(newSecondPassword: TextFieldValue) {
        val currentState = _state.value
        if (currentState is ProfileEditState.Screen) {
            val currentData = currentState.data

            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    secondPassword = currentData.secondPassword.copy(
                        text = if (newSecondPassword.text.length <= MAX_CHARACTERS) newSecondPassword else currentData.secondPassword.text,
                        subtitleVisibility = newSecondPassword.text.length >= MAX_CHARACTERS,
                        style = TextFieldCardStyles.Standard
                    )
                )
            )
        }
    }

    /** Сокрытие AlertDialog с UI */
    fun dismissAlertDialog() {
        val currentState = _state.value
        if (currentState is ProfileEditState.Screen) {
            val currentData = currentState.data
            _state.value = ProfileEditState.Screen(
                data = currentData.copy(
                    isShowErrorAlert = false
                )
            )
        }
    }

    /** Создание domain-модели для редактирования профиля */
    private fun createNewClientInfoDomain(currentData: ProfileEditScreen): NewClientInfoDomain =
        NewClientInfoDomain(
            name = currentData.name.text.text,
            surname = currentData.surname.text.text,
            role = if (currentData.roleChips.selectedChipRole == RoleChip.QA) NewClientRole.QA else NewClientRole.DEVELOPER,
            newPassword = currentData.firstPassword.text.text.ifBlank { null }
        )

    companion object {
        private const val TAG = "ProfileEditViewModel"
        private const val MAX_CHARACTERS = 64
        private const val MIN_PASSWORD_SIZE = 8
        private const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,64}\$"

        const val DEVELOPER_ID = 0
        const val QA_ID = 1
    }
}