package com.example.shopping_list.data.dataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shopping_list.data.models.Product
import io.reactivex.Flowable

@Dao
abstract class ProductDao {

    @Query("SELECT * FROM Product WHERE isPurchase == 1 ")
    abstract fun getAllPurchaseProducts(): Flowable<List<Product>>

    @Query("SELECT * FROM Product WHERE isPurchase == 0")
    abstract fun getAllNotPurchaseProducts(): Flowable<List<Product>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProduct(product: Product)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProducts(product: List<Product>)
}