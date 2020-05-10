package com.example.shopping_list.di.module.ui

import com.example.shopping_list.data.Repository
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.ui.addItem.AddItemActivity
import com.example.shopping_list.ui.addItem.AddItemPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class AddItemActivityModule {
    @ContributesAndroidInjector(modules = [AddItemActivityPresenterModule::class])
    abstract fun providesAddItemActivity(): AddItemActivity
}

@Module
class AddItemActivityPresenterModule {
    @Provides
    fun postAddItemActivityPresenter(repository: Repository) = AddItemPresenter(repository)
}