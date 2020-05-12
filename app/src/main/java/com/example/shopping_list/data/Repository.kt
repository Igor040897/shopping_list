package com.example.shopping_list.data

import android.net.Uri
import com.example.shopping_list.data.models.Product
import io.reactivex.Flowable
import java.io.File

interface Repository {

    val allPurchaseProducts: Flowable<List<Product>>

    val allNotPurchaseProducts: Flowable<List<Product>>

    fun saveItemProduct(product: Product)

    fun saveItemsProduct(products: List<Product>)

    fun getTempImageFileUri(name: String): Uri?

    fun saveImageToFile(uri: Uri): File?

}