package cdr.profilelib.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import cdr.profilelib.data.interactor.ProfileInteractor
import cdr.profilelib.data.interactor.ProfileInteractorImpl
import cdr.profilelib.data.mapper.ProfileMapper
import cdr.profilelib.data.mapper.ProfileMapperImpl
import cdr.profilelib.data.repository.ProfileRepository
import cdr.profilelib.data.repository.ProfileRepositoryImpl
import cdr.profilelib.presentation.edit.ProfileEditViewModel
import cdr.profilelib.presentation.main.ProfileViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * [Module] для функционала модуля профиля
 *
 * @author Alexandr Chekunkov
 */
@Module
internal object ProfileModule {

    @Provides
    fun provideProfileMapper(): ProfileMapper = ProfileMapperImpl()

    @Provides
    fun provideProfileRepository(profileMapper: ProfileMapper): ProfileRepository =
        ProfileRepositoryImpl(profileMapper)

    @Provides
    fun provideProfileInteractor(profileRepository: ProfileRepository): ProfileInteractor =
        ProfileInteractorImpl(profileRepository)

    @Provides
    @Named("ProfileViewModel")
    fun provideProfileViewModelFactory(projectInteractor: ProfileInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return ProfileViewModel(projectInteractor) as T
            }
        }

    @Provides
    @Named("ProfileEditViewModel")
    fun provideProfileEditViewModelFactory(projectInteractor: ProfileInteractor): ViewModelProvider.Factory =
        object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {

                return ProfileEditViewModel(projectInteractor) as T
            }
        }
}