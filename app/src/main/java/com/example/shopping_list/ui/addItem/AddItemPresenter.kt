package com.example.shopping_list.ui.addItem

import android.net.Uri
import com.example.shopping_list.data.Repository
import com.example.shopping_list.data.models.Product

class AddItemPresenter(private val repository: Repository) : AddItemContract.Presenter {

    //    private lateinit var view: AddItemContract.View
    private var imageUri: Uri? = null

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: AddItemContract.View) {

    }

    override fun saveItemProduct(nameProduct: String) {
        val product = Product(nameProduct)
        imageUri?.apply {
            repository.saveImageToFile(this)?.apply {
                product.imageUri = absolutePath
            }
        }
        repository.saveItemProduct(product)
    }

    fun saveImage(uri: Uri) {
        imageUri = uri
    }
}