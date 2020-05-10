package com.example.shopping_list.ui.addItem

import com.example.shopping_list.ui.base.BaseContract

class AddItemContract {
    interface View: BaseContract.View {
        fun onAddItemClick()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun saveItemProduct(product : String)
    }
}