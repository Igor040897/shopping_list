package com.example.shopping_list.data.dataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopping_list.data.models.Product
import io.reactivex.Maybe

@Dao
abstract class ProductDao {

    @Query("SELECT * FROM Product WHERE isPurchase == 0")
    abstract fun getAllPurchaseProducts(): Maybe<List<Product>>

    @Query("SELECT * FROM Product WHERE isPurchase == 1")
    abstract fun getAllNotPurchaseProducts(): Maybe<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProduct(product: Product)
}