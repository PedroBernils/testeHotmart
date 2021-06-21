package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.utils.Misc
import kotlin.random.Random

class HomeLocationAdapter(
    private val context: Context,
    var locationList:  ArrayList<String>
): RecyclerView.Adapter<HomeLocationAdapter.LocationHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_location_cell, parent, false)
        return LocationHolder(view)
    }

    override fun onBindViewHolder(holder: LocationHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class LocationHolder(val item: View): RecyclerView.ViewHolder(item) {

        fun bind(position: Int) {

            var locationPhoto = item.findViewById<ImageView>(R.id.locationPhoto)

            if (position % 2 == 0) {
                var layoutParams = locationPhoto.layoutParams
                layoutParams.height = 150
                locationPhoto.layoutParams = layoutParams
            }

            locationPhoto.setBackgroundColor(Misc.getRandomColor(context))
        }
    }
}