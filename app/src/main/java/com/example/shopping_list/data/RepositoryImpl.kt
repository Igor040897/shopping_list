package com.example.shopping_list.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.shopping_list.data.dataBase.DbStorageManager
import com.example.shopping_list.data.models.Product
import com.example.shopping_list.postSuccessResult
import io.reactivex.Maybe
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(
    private val dbStorageManager: DbStorageManager
//    private val fileWorker: FileWorker
) : Repository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override val allPurchaseProducts: Maybe<List<Product>>
        get() = dbStorageManager.getAllPurchaseProducts()

    override val allNotPurchaseProducts: Maybe<List<Product>>
        get() = dbStorageManager.getAllNotPurchaseProducts()

    override fun saveItemProduct(product: Product) {
        launch(Dispatchers.IO) {
            dbStorageManager.saveItemProduct(product)
        }
    }

//    private fun <T> retrieveDataFromDatabaseDefault(
//        dataFetch: suspend () -> LiveData<T>
//    ): LiveData<ResultObject<T>> {
//        val data = MediatorLiveData<ResultObject<T>>()
//        val job = launch { data.postSuccessResult(dataFetch()) }
//        return retrieveDataHelper(data, job)
//    }
//
//    private fun <T> retrieveDataHelper(
//        data: MediatorLiveData<ResultObject<T>>,
//        job: Job
//    ): LiveData<ResultObject<T>> {
//        val processing: ResultObject.Processing?
//        processing = ResultObject.Processing(job)
//        if (!job.isCompleted) {
//            data.postValue(processing)
//        }
//        return data
//    }

    companion object {
        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(
            dbStorageManager: DbStorageManager
        ): Repository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryImpl(dbStorageManager).also { INSTANCE = it }
            }
        }
    }
}