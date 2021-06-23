package com.pbernils.testehotmart.ui.home

import android.content.Context
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

    inner class LocationHolder(val item: View): RecyclerView.ViewHolder(item) {

        private val photo = item.findViewById<ImageView>(R.id.location_photo)
        private val name = item.findViewById<TextView>(R.id.location_name)
        private val type = item.findViewById<TextView>(R.id.location_type)
        private val rating = item.findViewById<TextView>(R.id.location_rating)
        private val stars = listOf<ImageView>(
            itemView.findViewById(R.id.rating_1),
            itemView.findViewById(R.id.rating_2),
            itemView.findViewById(R.id.rating_3),
            itemView.findViewById(R.id.rating_4),
            itemView.findViewById(R.id.rating_5)
        )

        init {
            item.setOnClickListener {
                onItemClicked(adapterPosition)
            }
        }

        fun bind() {

            var location = data!![adapterPosition]

            location.photo?: photo.setBackgroundColor(Misc.getRandomColor(context))

            name.text = location.name
            type.text = location.type
            val ratingValue = location.rating
            rating.text = "$ratingValue"

            RatingHelper.displayRating(ratingValue.toInt(), stars)

            if (adapterPosition % 2 == 0) {
                var layoutParams = photo.layoutParams
                layoutParams.height = 150
                photo.layoutParams = layoutParams
            }
        }
    }
}