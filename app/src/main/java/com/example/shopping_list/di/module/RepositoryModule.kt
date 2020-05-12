package com.example.shopping_list.di.module

import com.example.shopping_list.data.FileWorker
import com.example.shopping_list.data.dataBase.DbStorageManager
import com.example.shopping_list.data.Repository
import com.example.shopping_list.data.RepositoryImpl
import com.example.shopping_list.data.dataBase.AppDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DataBaseModule::class, FileWorkerModule::class])
class RepositoryModule {
    @Provides
    @Singleton
    fun provideRepository(database: AppDataBase, fileWorker: FileWorker): Repository {
        val dbStorageManager = DbStorageManager.getInstance(database)
        return RepositoryImpl.getInstance(dbStorageManager, fileWorker)
    }
}