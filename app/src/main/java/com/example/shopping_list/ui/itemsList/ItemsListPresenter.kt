package com.example.shopping_list.ui.itemsList

import com.example.shopping_list.data.Repository
import com.example.shopping_list.ui.main.purchase.PurchaseAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ItemsListPresenter(private val repository: Repository): ItemsListContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var view: ItemsListContract.View

    private lateinit var purchaseAdapter : PurchaseAdapter

    override fun subscribe() {
        setupPurchaseAdapter()
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun attach(view: ItemsListContract.View) {
        this.view = view
    }

    private fun setupPurchaseAdapter() {
        purchaseAdapter = PurchaseAdapter()

        disposables.add(repository.allNotPurchaseProducts
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    it?.run {
                        purchaseAdapter.setItems(this)
                    }
                },
                {

                })
        )

        view.postPurchaseAdapter(purchaseAdapter)
    }
}