package com.example.shopping_list.data

import com.example.shopping_list.data.dataBase.AppDataBase

class DbStorageManager private constructor(
    private val appDataBase: AppDataBase
) {
    companion object {

        @Volatile
        private var INSTANCE: DbStorageManager? = null

        fun getInstance(appDataBase: AppDataBase): DbStorageManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: DbStorageManager(appDataBase).also { INSTANCE = it }
            }
        }
    }
}