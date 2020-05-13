package com.example.shopping_list.ui.addItem.addPhoto

import android.net.Uri
import com.example.shopping_list.data.Repository

private const val RESULT_FILE_NAME = "user_pic"

class AddPhotoPresenter(private val repository: Repository) : AddPhotoContract.Presenter {

    private lateinit var view: AddPhotoContract.View

    var imageUri: Uri? = null
        set(value) {
            field = value
            view.returnPhoto(field)
        }

    private var photoUri: Uri? = null

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: AddPhotoContract.View) {
        this.view = view
    }

    override fun getTempImageFileUri() =
        photoUri ?: repository.getTempImageFileUri(RESULT_FILE_NAME)?.also {
            photoUri = it
        }


    fun photoTaken() {
        view.returnPhoto(photoUri)
    }
}