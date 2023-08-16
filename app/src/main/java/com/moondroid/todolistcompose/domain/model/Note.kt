package com.moondroid.todolistcompose.domain.model

import com.moondroid.todolistcompose.common.BoxColor

data class Note(
    val id: Int = 0,
    val description: String,
    val date: Long,
    val boxColor: BoxColor,
)
