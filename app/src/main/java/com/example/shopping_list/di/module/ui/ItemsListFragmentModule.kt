package com.example.shopping_list.di.module.ui

import com.example.shopping_list.data.Repository
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.ui.itemsList.ItemsListFragment
import com.example.shopping_list.ui.itemsList.ItemsListPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class ItemsListFragmentModule {
    @ContributesAndroidInjector(modules = [ItemsListFragmentPresenterModule::class])
    abstract fun providesItemsListFragment(): ItemsListFragment
}

@Module
class ItemsListFragmentPresenterModule {
    @Provides
    fun postItemsListFragmentPresenter(repository: Repository) = ItemsListPresenter(repository)
}