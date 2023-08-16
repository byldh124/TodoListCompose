package com.moondroid.todolistcompose.common

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.util.Log

object Extensions {
    inline fun <reified T : Parcelable> Bundle.parcelable(key: String): T? = when {
        Build.VERSION.SDK_INT >= 33 -> getParcelable(key, T::class.java)
        else -> @Suppress("DEPRECATION") getParcelable(key) as? T
    }
}
