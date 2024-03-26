package com.example.fitnesstracker

import android.graphics.Color
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class MainItem(
    val id: Int,
    @DrawableRes val drawableId: Int,
    @StringRes val textStringId: Int,
    val color: Int
)
