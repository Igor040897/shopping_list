package com.example.shopping_list.di.module.ui

import com.example.shopping_list.data.Repository
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.ui.history.HistoryPresenter
import com.example.shopping_list.ui.history.HistoryShoppingFragment
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class HistoryShoppingFragmentModule {
    @ContributesAndroidInjector(modules = [HistoryShoppingFragmentPresenterModule::class])
    abstract fun providesHistoryShoppingFragment(): HistoryShoppingFragment
}

@Module
class HistoryShoppingFragmentPresenterModule {
    @Provides
    fun postHistoryShoppingFragmentPresenter(repository: Repository) = HistoryPresenter(repository)
}