package com.example.shopping_list.data.dataBase

import com.example.shopping_list.data.models.Product

class DbStorageManager private constructor(
    private val appDataBase: AppDataBase
) {

    fun saveItemProduct(product: Product) {
        appDataBase.productDao().insertProduct(product)
    }

    fun getAllPurchaseProducts() = appDataBase.productDao().getAllPurchaseProducts()

    fun getAllNotPurchaseProducts() = appDataBase.productDao().getAllNotPurchaseProducts()

    companion object {

        @Volatile
        private var INSTANCE: DbStorageManager? = null

        fun getInstance(appDataBase: AppDataBase): DbStorageManager {
            return INSTANCE
                ?: synchronized(this) {
                INSTANCE
                    ?: DbStorageManager(
                        appDataBase
                    )
                        .also { INSTANCE = it }
            }
        }
    }
}