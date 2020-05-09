package com.example.shopping_list.ui.main

import com.example.shopping_list.data.Repository

class MainPresenter(val repository: Repository): MainContract.Presenter {

    private lateinit var view: MainContract.View

    override fun onViewCreated() {
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
    }

    override fun attach(view: MainContract.View) {
        this.view = view
    }
}