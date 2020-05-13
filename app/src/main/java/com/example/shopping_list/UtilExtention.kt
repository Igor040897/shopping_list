package com.example.shopping_list

import android.R.attr.bitmap
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import com.example.shopping_list.data.IMAGE_EXTENSION
import java.io.File
import java.io.IOException

fun ViewGroup.inflateView(@LayoutRes layout: Int): View {
    return LayoutInflater.from(this.context).inflate(layout, this, false)
}

@Throws(IOException::class)
fun Context.createImageFile(name: String): File {
    // Create an image file name
    return File.createTempFile(
        name,
        IMAGE_EXTENSION,
        cacheDir
    )
}

fun Bitmap.rotateBitmap(angle: Float): Bitmap {
    val matrix = Matrix()
    matrix.postRotate(angle)
    return Bitmap.createBitmap(
        this, 0, 0, width, height,
        matrix, true
    )
}