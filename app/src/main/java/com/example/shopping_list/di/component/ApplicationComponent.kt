package com.example.shopping_list.di.component

import android.app.Application
import com.example.shopping_list.App
import com.example.shopping_list.di.module.DataBaseModule
import com.example.shopping_list.di.module.ui.MainActivityModule
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.di.module.ui.AddItemActivityModule
import com.example.shopping_list.di.module.ui.HistoryShoppingFragmentModule
import com.example.shopping_list.di.module.ui.ItemsListFragmentModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AndroidSupportInjectionModule::class,
        DataBaseModule::class,
        RepositoryModule::class,
        MainActivityModule::class,
        AddItemActivityModule::class,
        ItemsListFragmentModule::class,
        HistoryShoppingFragmentModule::class
    ]
)
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: App)
}