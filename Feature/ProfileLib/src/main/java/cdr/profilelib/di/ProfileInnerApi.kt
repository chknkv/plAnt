package cdr.profilelib.di

import androidx.lifecycle.ViewModelProvider
import javax.inject.Named

/**
 * Внутренее API для модуля профиля
 *
 * @author Alexandr Chekunkov
 */
internal interface ProfileInnerApi {

    @Named("ProfileViewModel")
    fun getProfileViewModelFactory(): ViewModelProvider.Factory

    @Named("ProfileEditViewModel")
    fun getProfileEditViewModelFactory(): ViewModelProvider.Factory
}