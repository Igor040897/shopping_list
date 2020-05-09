package com.example.shopping_list.di.module.ui

import com.example.shopping_list.data.Repository
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.ui.main.MainActivity
import com.example.shopping_list.ui.main.MainPresenter
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module(includes = [RepositoryModule::class])
abstract class MainActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityPresenterModule::class])
    abstract fun providesMainActivity(): MainActivity
}

@Module
class MainActivityPresenterModule {
    @Provides
    fun postMainActivityPresenter(repository: Repository): MainPresenter {
        return MainPresenter(repository)
    }
}