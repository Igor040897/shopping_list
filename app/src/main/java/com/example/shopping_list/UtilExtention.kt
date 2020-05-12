package com.example.shopping_list

import android.content.Context
import com.example.shopping_list.data.IMAGE_EXTENSION
import java.io.File
import java.io.IOException

@Throws(IOException::class)
fun Context.createImageFile(name: String): File {
    // Create an image file name
    return File.createTempFile(
        name,
        IMAGE_EXTENSION,
        cacheDir
    )
}