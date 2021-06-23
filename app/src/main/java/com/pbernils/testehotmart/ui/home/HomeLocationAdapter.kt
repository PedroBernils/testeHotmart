package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.model.Location
import com.pbernils.testehotmart.utils.Misc
import com.pbernils.testehotmart.utils.RatingHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home_location_cell.view.*

class HomeLocationAdapter(
    private val context: Context,
    private val onItemClicked: (Int) -> Unit
): RecyclerView.Adapter<HomeLocationAdapter.LocationHolder>() {

    var data: List<Location>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_location_cell, parent, false)
        return LocationHolder(view)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class LocationHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val stars = listOf(
            itemView.rating_1,
            itemView.rating_2,
            itemView.rating_3,
            itemView.rating_4,
            itemView.rating_5
        )

        init {
            itemView.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind() {

            var location = data!![adapterPosition]
            location.grabPhoto()

            val color = Misc.getRandomColor(context)

            if (location.photo.isNullOrBlank()) {
                itemView.location_photo.setBackgroundColor(color)
            } else {
                val colorDrawable = ColorDrawable(color)
                Picasso.get()
                    .load(location.photo)
                    .placeholder(colorDrawable)
                    .error(colorDrawable)
                    .into(itemView.location_photo)
            }

            itemView.location_name.text = location.name
            itemView.location_type.text = location.type
            val ratingValue = location.rating
            itemView.location_rating.text = "$ratingValue"

            RatingHelper.displayRating(ratingValue.toInt(), stars)
        }
    }
}