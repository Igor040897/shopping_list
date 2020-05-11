package com.example.shopping_list.ui.history

import com.example.shopping_list.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class HistoryPresenter(private val repository: Repository): HistoryContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var view: HistoryContract.View

    private var productsAdapter = ProductsAdapter()

    init {
        disposables.add(repository.allPurchaseProducts
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                it?.run {
                    productsAdapter.setItems(this)
                }
            }
        )
    }

    override fun subscribe() {
        view.postProductAdapter(productsAdapter)
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun attach(view: HistoryContract.View) {
        this.view = view
    }
}