package com.example.shopping_list.ui.addItem.addPhoto

import android.net.Uri
import com.example.shopping_list.ui.base.BaseContract

class AddPhotoContract {
    interface View: BaseContract.View {
        fun onMakePhotoClick()
        fun onUploadPhotoFromGalleryClick()
        fun returnPhoto(uri: Uri?)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getTempImageFileUri(): Uri?
    }
}