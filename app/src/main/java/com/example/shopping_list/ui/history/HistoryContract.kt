package com.example.shopping_list.ui.history

import com.example.shopping_list.ui.base.BaseContract

class HistoryContract {
    interface View: BaseContract.View {
        fun postProductAdapter(adapter: ProductsAdapter)
    }

    interface Presenter: BaseContract.Presenter<View> {

    }
}