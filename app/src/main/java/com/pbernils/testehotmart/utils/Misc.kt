package com.pbernils.testehotmart.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.pbernils.testehotmart.R

object Misc {

    fun getRandomColor(context: Context): Int {

        return ContextCompat.getColor(context, listOf(
                R.color.duck_egg_blue,
                R.color.light_pink,
                R.color.creme
        ).random())

    }
}