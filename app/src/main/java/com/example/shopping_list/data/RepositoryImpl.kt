package com.example.shopping_list.data

class RepositoryImpl(
    private val dbStorageManager: DbStorageManager
//    private val fileWorker: FileWorker
) : Repository {
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