package com.pbernils.testehotmart.utils

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
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

    fun getRandomImageUrl(): String {
        return listOf(
            "https://i.imgur.com/VInCihc.jpg",
            "https://i.imgur.com/qbM26PP.jpg",
            "https://i.imgur.com/mtGztWo.jpg",
            "https://i.imgur.com/ZhV0gYQ.jpg",
            "https://i.imgur.com/bztxGmE.png",
            ""
        ).random()
    }

    fun getStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId: Int = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun getNavigationBarHeight(context: Context): Int {
        var result = 0
        val resourceId: Int = context.resources.getIdentifier("navigation_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    fun convertDpToPixel(dp: Float, context: Context?): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            dp * (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }

    fun convertPixelsToDp(px: Float, context: Context?): Float {
        return if (context != null) {
            val resources = context.resources
            val metrics = resources.displayMetrics
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        } else {
            val metrics = Resources.getSystem().displayMetrics
            px / (metrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
        }
    }
}