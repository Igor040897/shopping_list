package com.example.shopping_list.ui.base

//todo
import io.reactivex.disposables.CompositeDisposable

class BaseContract {

    interface Presenter<in T> {
        //todo refactoring
//        protected val disposables: CompositeDisposable = CompositeDisposable()

        fun subscribe()
        fun unsubscribe()
        fun attach(view: T)
        //todo remove view after detach
    }

    interface View {

    }
}