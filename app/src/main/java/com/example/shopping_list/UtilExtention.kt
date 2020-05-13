package com.example.shopping_list

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Matrix
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
    return File.createTempFile(
        name,
        IMAGE_EXTENSION,
        cacheDir
    )
}