package com.example.shopping_list.ui.main

import com.example.shopping_list.ui.base.BaseContract

class MainContract {

    interface View: BaseContract.View {
    }

    interface Presenter: BaseContract.Presenter<View> {
    }
}