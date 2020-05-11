package com.example.shopping_list.ui.itemsList

import com.example.shopping_list.ui.base.BaseContract

class ItemsListContract {
    interface View: BaseContract.View {
        fun postPurchaseAdapter(adapterSelectMode: SelectModeProductsAdapter)
        fun onLongClicked()
        fun onClicked(size : Int)
    }

    interface Presenter: BaseContract.Presenter<View> {
    }
}