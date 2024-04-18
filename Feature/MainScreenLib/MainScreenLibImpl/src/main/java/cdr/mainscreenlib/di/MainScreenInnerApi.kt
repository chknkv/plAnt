package cdr.mainscreenlib.di

import cdr.mainscreenlib.presentation.chat.ChatViewModel
import cdr.mainscreenlibapi.di.MainScreenApi

/**
 * Внутренее API для модуля авторизации
 *
 * @author Alexandr Chekunkov
 */
internal interface MainScreenInnerApi : MainScreenApi {

    fun getChatViewModel(): ChatViewModel

}