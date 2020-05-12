package com.example.shopping_list.di.module.ui

import com.example.shopping_list.data.Repository
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.ui.addItem.addPhoto.AddPhotoDialogFragment
import com.example.shopping_list.ui.addItem.addPhoto.AddPhotoPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class AddPhotoDialogFragmentModule {
    @ContributesAndroidInjector(modules = [AddPhotoDialogPresenterModule::class])
    abstract fun providesAddPhotoDialogFragment(): AddPhotoDialogFragment
}

@Module
class AddPhotoDialogPresenterModule {
    @Provides
    fun postAddPhotoPresenter(repository: Repository) = AddPhotoPresenter(repository)
}