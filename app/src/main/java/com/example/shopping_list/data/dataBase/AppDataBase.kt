package com.example.shopping_list.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopping_list.data.models.PurchaseItem

const val NAME_DATABASE = "database-app"

const val VERSION_DATABASE_1 = 1
const val CURRENT_VERSION_DATABASE = VERSION_DATABASE_1

@Database(
    entities = [PurchaseItem::class],
    version = CURRENT_VERSION_DATABASE,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(
            context: Context
        ): AppDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room
                    .databaseBuilder(context, AppDataBase::class.java, NAME_DATABASE)
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}