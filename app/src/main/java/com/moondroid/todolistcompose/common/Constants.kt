package com.moondroid.todolistcompose.common

import androidx.compose.ui.graphics.Color
import com.moondroid.todolistcompose.presentation.ui.theme.*

enum class BoxColor(val color: Color) {
    YELLOW(Memo01),
    PINK(Memo02),
    BLUE(Memo03),
    GREEN(Memo04),
    RED(Memo05);

    companion object {
        fun getRandom(): BoxColor {
            return BoxColor.values().toList().shuffled().first()
        }
    }

}