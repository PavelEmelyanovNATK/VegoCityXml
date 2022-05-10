package com.emelyanov.vegocityxml.shared.domain.utils
import android.content.res.Resources
import kotlin.math.roundToInt


val Float.dp: Float
    get() = this * Resources.getSystem().displayMetrics.density

val Int.dp: Int
    get() = (this.toFloat() * Resources.getSystem().displayMetrics.density).roundToInt()