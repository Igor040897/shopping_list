package com.example.shopping_list.ui.main

import com.example.shopping_list.data.Repository
import com.example.shopping_list.ui.main.purchase.PurchaseAdapter

class MainPresenter(private val repository: Repository): MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }
}