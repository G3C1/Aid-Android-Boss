package com.g3c1.aide.feature_store.presentation.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

@SuppressLint("Range")
fun Uri.getPath(context: Context): String? {
    val cursor =
        context.contentResolver.query(this, arrayOf(MediaStore.Images.Media.DATA), null, null, null)
    cursor?.moveToNext()
    val path = cursor?.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA))
    cursor?.close()
    return path
}

fun File.toRequestBody(): MultipartBody.Part {
    val requestFile = RequestBody.create(MediaType.parse("image/*"), this)
    return MultipartBody.Part.createFormData("file", this.name, requestFile)
}