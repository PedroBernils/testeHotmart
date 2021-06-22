package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.utils.Misc
import kotlin.random.Random

class HomeLocationAdapter(
    private val context: Context,
    var data:  ArrayList<String>,
    private val onItemClicked: (View) -> Unit
): RecyclerView.Adapter<HomeLocationAdapter.LocationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_location_cell, parent, false)
        return LocationHolder(view)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 12
    }

    inner class LocationHolder(item: View): RecyclerView.ViewHolder(item) {

        private var color = 0
        private val photo = item.findViewById<ImageView>(R.id.location_photo)
        private val name = item.findViewById<TextView>(R.id.location_name)
        private val type = item.findViewById<TextView>(R.id.location_type)
        private val rating = item.findViewById<TextView>(R.id.location_rating)
        private val star1 = itemView.findViewById<ImageView>(R.id.rating_1)
        private val star2 = itemView.findViewById<ImageView>(R.id.rating_2)
        private val star3 = itemView.findViewById<ImageView>(R.id.rating_3)
        private val star4 = itemView.findViewById<ImageView>(R.id.rating_4)
        private val star5 = itemView.findViewById<ImageView>(R.id.rating_5)

        init {
            item.setOnClickListener {
                onItemClicked(photo)
            }
        }

        fun bind() {

            if (adapterPosition % 2 == 0) {
                var layoutParams = photo.layoutParams
                layoutParams.height = 150
                photo.layoutParams = layoutParams
            }
        }
    }
}