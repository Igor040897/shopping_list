package com.example.shopping_list.ui.addItem

import android.net.Uri
import com.example.shopping_list.ui.base.BaseContract

class AddItemContract {
    interface View: BaseContract.View {
        fun onAddItemClick()
        fun onAddPhotoClick()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun saveItemProduct(nameProduct : String)
        fun saveImage(uri: Uri)
        fun hasImage(): Boolean
    }
}