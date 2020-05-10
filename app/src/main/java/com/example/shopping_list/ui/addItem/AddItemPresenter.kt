package com.example.shopping_list.ui.addItem

import com.example.shopping_list.data.Repository
import com.example.shopping_list.data.models.Product

class AddItemPresenter(private val repository: Repository): AddItemContract.Presenter {
    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: AddItemContract.View) {

    }

    override fun saveItemProduct(product: String) {
        repository.saveItemProduct(Product(product))
    }
}