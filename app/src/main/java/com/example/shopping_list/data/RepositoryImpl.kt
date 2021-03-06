package com.example.shopping_list.data

import android.net.Uri
import com.example.shopping_list.data.dataBase.DbStorageManager
import com.example.shopping_list.data.models.Product
import io.reactivex.Flowable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.File
import java.util.*
import kotlin.coroutines.CoroutineContext

class RepositoryImpl(
    private val dbStorageManager: DbStorageManager,
    private val fileWorker: FileWorker
) : Repository, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override val allPurchaseProducts: Flowable<List<Product>>
        get() = dbStorageManager.getAllPurchaseProducts()

    override val allNotPurchaseProducts: Flowable<List<Product>>
        get() = dbStorageManager.getAllNotPurchaseProducts()

    override fun saveItemProduct(product: Product) {
        val job = SupervisorJob()
        launch(Dispatchers.IO + job) {
            dbStorageManager.saveItemProduct(product)
        }
    }

    override fun saveItemsProduct(products: List<Product>) {
        val job = SupervisorJob()
        launch(Dispatchers.IO + job) {
            dbStorageManager.saveItemsProduct(products)
        }
    }

    override fun getTempImageFileUri(name: String) = fileWorker.getTempImageFileUri(name)

    override fun saveImageToFile(uri: Uri): File? = fileWorker.saveImage(uri, Date().toString())?.let {
        val file = File(it)
        if (file.exists()) {
            file
        } else null
    }

    companion object {
        @Volatile
        private var INSTANCE: RepositoryImpl? = null

        fun getInstance(
            dbStorageManager: DbStorageManager,
            fileWorker: FileWorker
        ): Repository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: RepositoryImpl(dbStorageManager, fileWorker).also { INSTANCE = it }
            }
        }
    }
}