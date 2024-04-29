package cdr.mainscreenlib.di.inner

import cdr.mainscreenlib.presentation.chat.ChatViewModel

/**
 * Внутренее API для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface MainScreenInnerApi {

    fun getChatViewModel(): ChatViewModel

}