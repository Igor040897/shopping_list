package com.example.shopping_list.di.module

import android.app.Application
import com.example.shopping_list.data.FileWorker
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FileWorkerModule {

    @Provides
    @Singleton
    fun provideFileWorker(application: Application): FileWorker {
        return FileWorker.getInstance(application)
    }
}