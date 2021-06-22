package com.pbernils.testehotmart.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.pbernils.testehotmart.R

object Misc {

    fun getRandomColor(context: Context): Int {

        return ContextCompat.getColor(
            context, listOf(
                R.color.duck_egg_blue,
                R.color.light_pink,
                R.color.creme
            ).random()
        )
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}