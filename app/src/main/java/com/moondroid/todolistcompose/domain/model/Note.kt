package com.moondroid.todolistcompose.domain.model

import android.os.Parcelable
import com.moondroid.todolistcompose.common.BoxColor
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    var description: String,
    val date: Long,
    var boxColor: BoxColor,
) : Parcelable
