package com.example.shopping_list.ui.itemsList

import com.example.shopping_list.data.Repository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class ItemsListPresenter(private val repository: Repository) : ItemsListContract.Presenter {
    private val disposables: CompositeDisposable = CompositeDisposable()
    private lateinit var view: ItemsListContract.View

    private var selectModeProductsAdapter: SelectModeProductsAdapter =
        SelectModeProductsAdapter()

    init {
        selectModeProductsAdapter.onPurchaseClickListener =
            object : SelectModeProductsAdapter.OnPurchaseItemClickListener {
                override fun onLongClicked() {
                    view.onLongClicked()
                }

                override fun onClicked(size: Int) {
                    view.onClicked(size)
                }

            }
        disposables.add(
            repository.allNotPurchaseProducts
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    it?.run {
                        selectModeProductsAdapter.setItems(this)
                    }
                }
        )
    }

    override fun subscribe() {
        view.postPurchaseAdapter(selectModeProductsAdapter)
    }

    override fun unsubscribe() {
        disposables.clear()
    }

    override fun attach(view: ItemsListContract.View) {
        this.view = view
    }

    override fun clearItemSelections() {
        selectModeProductsAdapter.clearSelections()
    }

    override fun shopSelectItems() {
        val selectedItems = selectModeProductsAdapter.getSelectedItems()
        repository.saveItemsProduct(selectedItems)
    }

    override fun selectAll() = selectModeProductsAdapter.selectAll()
}