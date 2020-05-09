package com.example.shopping_list.di.module

import com.example.shopping_list.data.DbStorageManager
import com.example.shopping_list.data.Repository
import com.example.shopping_list.data.RepositoryImpl
import com.example.shopping_list.data.dataBase.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBaseModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(database: AppDataBase): Repository {
        val dbStorageManager = DbStorageManager.getInstance(database)
        return RepositoryImpl.getInstance(dbStorageManager)
    }
}