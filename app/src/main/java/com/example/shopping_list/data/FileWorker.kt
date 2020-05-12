package com.example.shopping_list.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import androidx.core.content.FileProvider
import com.example.shopping_list.BuildConfig
import com.example.shopping_list.createImageFile
//import com.bumptech.glide.Glide
//import com.client.marvel.BuildConfig
//import com.client.marvel.common.createImageFile
//import com.client.marvel.common.createPhotoTaskFile
//import com.client.marvel.data.model.adVideo.AdVideoFile
import java.io.*
import java.net.URL

private const val FILE_PROVIDER_AUTHORITY = "${BuildConfig.APPLICATION_ID}.fileprovider"
private const val FOLDER_IMAGE = "image"
const val IMAGE_EXTENSION = ".jpg"
//private const val FOLDER_VIDEO = "video"
private const val FOLDER_PHOTO_TASK = "Photo"
//const val AVATAR_FILE = "avatar.jpg"
private const val MAX_IMAGE_SIZE = 1000

class FileWorker(val context: Context) {

    companion object {
        @Volatile
        private var INSTANCE: FileWorker? = null

        fun getInstance(context: Context): FileWorker {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: FileWorker(context).also { INSTANCE = it }
            }
        }
    }

    fun getTempImageFileUri(name: String): Uri? = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
        generateExternalFileUri()
    } else {
        val photoFile: File? = try {
            context.createImageFile(name)
        } catch (ex: IOException) {
            null
        }
        // Continue only if the File was successfully created
        photoFile?.let { file ->
            FileProvider.getUriForFile(
                context,
                FILE_PROVIDER_AUTHORITY,
                file
            )
        }
    }
//
//    fun getPhotoFileUri(name: String): Uri? = if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//        generateExternalFileUri()
//    } else {
//        val photoFile: File? = try {
//            context.createPhotoTaskFile(name)
//        } catch (ex: IOException) {
//            null
//        }
//        photoFile?.let { file ->
//            FileProvider.getUriForFile(
//                    context,
//                    FILE_PROVIDER_AUTHORITY,
//                    file
//            )
//        }
//    }
//
    private fun generateExternalFileUri(): Uri {
        val file = File(
            getPhotoDirectory().path + "/" + FOLDER_IMAGE
                    + System.currentTimeMillis() + IMAGE_EXTENSION
        )
        return Uri.fromFile(file)
    }

    private fun getPhotoDirectory() = File(
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
        FOLDER_PHOTO_TASK
    ).apply {
        if (!exists())
            mkdirs()
    }
//
    fun saveImage(image: Uri, name: String): String? {
        var savedImagePath: String? = null
        var bitmap = try {
            MediaStore.Images.Media.getBitmap(context.contentResolver, image)
        } catch (e: Exception) {
            null
        }
        if (bitmap != null && bitmap.width > MAX_IMAGE_SIZE) {
            bitmap = Bitmap.createScaledBitmap(bitmap, MAX_IMAGE_SIZE, MAX_IMAGE_SIZE, false)
        }
        bitmap?.also {
            savedImagePath = saveBitmapToFile(it, name)
        }
        return savedImagePath
    }

    fun saveBitmapToFile(bitmap: Bitmap, fileName: String): String? {
        var filePath: String? = null
        getImageDir()?.run {
            val imageFile = File(this, fileName).apply {
                if (isFile) delete()
            }
            filePath = imageFile.absolutePath
            try {
                val fOut = FileOutputStream(imageFile)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut)
                fOut.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return filePath
    }
//
//    private fun loadImageFromFileDir(): Bitmap? {
//        var bitmap: Bitmap? = null
//        getImageDir()?.apply {
//            val imageFile = File(this, AVATAR_FILE)
//            try {
//                val fOut = FileInputStream(imageFile)
//                bitmap = BitmapFactory.decodeStream(fOut)
//                fOut.close()
//
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//        return bitmap
//    }
//
    private fun getImageDir(): File? {
        val storageDir = File(context.filesDir, FOLDER_IMAGE)
        val success = if (!storageDir.exists()) {
            storageDir.mkdirs()
        } else true
        return storageDir.takeIf { success }
    }
//
//    private fun getVideoDir(): File? {
//        val storageDir = File(context.filesDir, FOLDER_VIDEO)
//        val success = if (!storageDir.exists()) {
//            storageDir.mkdirs()
//        } else true
//        return storageDir.takeIf { success }
//    }
//
//    fun getCurrentAvatar() = getImageDir()?.let { dir ->
//        File(dir, AVATAR_FILE).takeIf(File::exists)
//    }
//
//    fun getCurrentAvatarBitmap() = loadImageFromFileDir()
//
//    fun clearAllDir() {
//        clearDir(context.filesDir)
//        clearDir(context.cacheDir)
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) clearExternalStoragePhotoTasksDir()
//    }
//
//    fun clearCacheDir() {
//        clearDir(context.cacheDir)
//    }
//
//    fun clearGlideMemory() {
//        Glide.get(context).clearMemory()
//    }
//
//    fun clearExternalStoragePhotoTasksDir() {
//        clearDir(getPhotoTaskDirectory())
//    }
//
//    fun clearVideo(localUrl: String) {
//        fun clearVideoInDir(dir: File, localUrl: String = "") {
//            dir.list()?.let { children ->
//                for (i in children.indices) {
//                    File(dir, children[i]).apply {
//                        if (isDirectory) clearVideoInDir(this, localUrl)
//                        if (isFile && absolutePath == localUrl) delete()
//                    }
//                }
//            }
//        }
//        context.filesDir.list()?.let { children ->
//            for (i in children.indices) {
//                val file = File(context.filesDir, children[i])
//                if (file.isDirectory && file.name == FOLDER_VIDEO) {
//                    clearVideoInDir(context.filesDir, localUrl)
//                }
//            }
//        }
//    }
//
//    private fun clearDir(dir: File, except: String = "") {
//        if (dir.isDirectory) {
//            dir.list()?.let { children ->
//                for (i in children.indices) {
//                    File(dir, children[i]).apply {
//                        if (isDirectory) clearDir(this, except)
//                        if (isFile && absolutePath != except) delete()
//                    }
//                }
//            }
//        }
//    }
//
//
//    fun getImageFile(fileName: String): File? {
//        val file = File("${getImageDir()}/$fileName")
//        return if (file.exists()) file else null
//    }
//
//    fun obtainFileByUri(context: Context, uri: Uri): File? {
//        try {
//            val inputStream = context.contentResolver.openInputStream(uri)
//            val fileName = getFileNameByUri(context, uri)
//            fileName?.run {
//                val splitName = splitFileName(this)
//                var tempFile = File.createTempFile(splitName[0], splitName[1])
//                tempFile = tempFile.rename(this)
//                tempFile.deleteOnExit()
//                var out: FileOutputStream? = null
//                try {
//                    out = FileOutputStream(tempFile)
//                } catch (e: FileNotFoundException) {
//                    e.printStackTrace()
//                }
//
//                if (inputStream != null && out != null) {
//                    inputStream.copy(out)
//                    inputStream.close()
//                } else {
//                    out?.close()
//                    inputStream?.close()
//                    return null
//                }
//                out.close()
//                inputStream.close()
//                return tempFile
//            }
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }
//        return null
//    }
//
//    private fun splitFileName(fileName: String): Array<String> {
//        var name = fileName
//        var extension = ""
//        val i = fileName.lastIndexOf(".")
//        if (i != -1) {
//            name = fileName.substring(0, i)
//            extension = fileName.substring(i)
//        }
//
//        return arrayOf(name, extension)
//    }
//
//    private fun getFileNameByUri(context: Context, uri: Uri): String? {
//        var result: String? = null
//        if (uri.scheme == "content") {
//            val cursor = context.contentResolver.query(uri, null, null, null, null)
//            try {
//                if (cursor != null && cursor.moveToFirst()) {
//                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME))
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            } finally {
//                cursor?.close()
//            }
//        }
//        if (result == null) {
//            result = uri.path
//            val cut = result.lastIndexOf(File.separator)
//            if (cut != -1) {
//                result = result.substring(cut + 1)
//            }
//        }
//        return result
//    }
//
//    private fun File.rename(newName: String): File {
//        val newFile = File(parent, newName)
//        if (newFile != this) {
//            if (newFile.exists() && newFile.delete()) {
//                Log.d("FileUtil", "Delete old $newName file")
//            }
//            if (this.renameTo(newFile)) {
//                Log.d("FileUtil", "Rename file to $newName")
//            }
//        }
//        return newFile
//    }
//
//    private fun InputStream.copy(output: FileOutputStream): Long {
//        var count: Long = 0
//        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
//        var n = read(buffer)
//        while (n > 0) {
//            output.write(buffer, 0, n)
//            count += n.toLong()
//            n = read(buffer)
//        }
//        return count
//    }
//
//    fun downloadVideo(file: AdVideoFile): String? {
//        val outputFile = File(getVideoDir(), "${file.startDate}${file.url.substringAfterLast("/")}")
//        return downloadFile(file.url, outputFile)
//    }
//
//    private fun downloadFile(url: String, outputFile: File): String? {
//        try {
//            LogUtil.d(VIDEO_AD_TAG, "start download video, uri = $url")
//            val u = URL(url)
//            u.openConnection()
//            var bytesRead = 0
//            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
//            val inputStream = u.openStream()
//            val outputStream = FileOutputStream(outputFile)
//            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
//                outputStream.write(buffer, 0, bytesRead)
//            }
//            inputStream.close()
//            outputStream.close()
//            LogUtil.d(VIDEO_AD_TAG, "end download video, uri = $url")
//            return outputFile.absolutePath
//        } catch (e: FileNotFoundException) {
//            LogUtil.d(VIDEO_AD_TAG, "error download video, uri = $url, e = $e")
//            return null
//        } catch (e: IOException) {
//            LogUtil.d(VIDEO_AD_TAG, "error download video, uri = $url, e = $e")
//            return null
//        } catch (e: NegativeArraySizeException) {
//            LogUtil.d(VIDEO_AD_TAG, "error download video, uri = $url, e = $e")
//            return null
//        }
//    }
}
