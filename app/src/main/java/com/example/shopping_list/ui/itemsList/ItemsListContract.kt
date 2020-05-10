package com.example.shopping_list.ui.itemsList

import com.example.shopping_list.ui.base.BaseContract
import com.example.shopping_list.ui.main.purchase.PurchaseAdapter

class ItemsListContract {
    interface View: BaseContract.View {
        fun postPurchaseAdapter(adapter: PurchaseAdapter)
    }

    interface Presenter: BaseContract.Presenter<View> {
    }
}