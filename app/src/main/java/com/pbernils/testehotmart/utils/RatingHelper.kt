package com.pbernils.testehotmart.utils

import android.widget.ImageView
import com.pbernils.testehotmart.R

object RatingHelper {

    fun displayRating(rating: Int, stars: List<ImageView>) {

        for (i in rating - 1 downTo 0) {
            stars[i].setImageResource(R.drawable.ic_star_small_on)
        }

        for (i in 4 downTo rating) {
            stars[i].setImageResource(R.drawable.ic_star_small_off)
        }

    }
}