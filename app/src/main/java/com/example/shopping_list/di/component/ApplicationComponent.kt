package com.example.shopping_list.di.component

import android.app.Application
import com.example.shopping_list.App
import com.example.shopping_list.di.module.DataBaseModule
import com.example.shopping_list.di.module.FileWorkerModule
import com.example.shopping_list.di.module.RepositoryModule
import com.example.shopping_list.di.module.ui.*
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
        FileWorkerModule::class,
        RepositoryModule::class,
        MainActivityModule::class,
        AddItemActivityModule::class,
        ItemsListFragmentModule::class,
        HistoryShoppingFragmentModule::class,
        AddPhotoDialogFragmentModule::class
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