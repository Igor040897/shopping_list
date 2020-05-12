package com.example.shopping_list.ui.addItem.addPhoto

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import com.example.shopping_list.R
import com.example.shopping_list.databinding.DialogFragmentAddPhotoBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

const val TAG = "AddPhotoDialogFragment"
private const val REQUEST_UPLOAD_PHOTO_FROM_GALLERY = 0
private const val REQUEST_TAKE_PHOTO = 1
private const val IMAGE_URI_KEY = "image_key"

class AddPhotoDialogFragment : BaseBottomSheetDialogFragment<DialogFragmentAddPhotoBinding>(),
    HasAndroidInjector, AddPhotoContract.View {

    companion object {
        fun getInstance(): AddPhotoDialogFragment =
            AddPhotoDialogFragment()
    }

    @Inject
    lateinit var fragmentInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = fragmentInjector

    override val contentLayoutId: Int
        get() = R.layout.dialog_fragment_add_photo

    @Inject
    lateinit var presenter: AddPhotoPresenter

    private var listener: OnDialogAddPhotoResultListener? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is OnDialogAddPhotoResultListener) {
            listener = context
        }
        presenter.attach(this)
    }

    override fun setupBinding(binding: DialogFragmentAddPhotoBinding) {
        binding.apply {
            actionListener = this@AddPhotoDialogFragment
        }
    }

    override fun onMakePhotoClick() {
        presenter.getTempImageFileUri()?.apply {
            callCameraActivity(this)
        }
    }

    private fun callCameraActivity(uri: Uri) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            val packageManager = activity?.packageManager ?: return@also
            takePictureIntent.resolveActivity(packageManager)?.also {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri)
                startActivityForResult(
                    takePictureIntent,
                    REQUEST_TAKE_PHOTO
                )
            }
        }
    }

    override fun onUploadPhotoFromGalleryClick() {
        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI).also {
            startActivityForResult(it, REQUEST_UPLOAD_PHOTO_FROM_GALLERY)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            REQUEST_UPLOAD_PHOTO_FROM_GALLERY -> {
                if (resultCode == RESULT_OK) {
                    presenter.imageUri = data?.data
                }
            }
            REQUEST_TAKE_PHOTO -> {
                if (resultCode == RESULT_OK) {
                    presenter.photoTaken()
                }
            }
        }
    }

    override fun returnPhoto(uri: Uri?) {
        if (uri != null) {
            listener?.resultPhoto(uri)
            dismiss()
        } else {
            Toast.makeText(context, getString(R.string.take_photo_error), Toast.LENGTH_LONG).show()
        }
    }
}