package com.pbernils.testehotmart.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.utils.Misc

class LocationDetailsPhotoAdapter(): RecyclerView.Adapter<LocationDetailsPhotoAdapter.PhotoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_details_photo_cell, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return 7
    }

    inner class PhotoHolder(item: View): RecyclerView.ViewHolder(item) {

        private val spacerLeft = item.findViewById<View>(R.id.spacer_left)
        private val photoImage = item.findViewById<ImageView>(R.id.photo)
        private val spacerRight = item.findViewById<View>(R.id.spacer_right)

        fun bind(position: Int) {

            if (position == 0) {
                spacerLeft.visibility = View.VISIBLE
            } else {
                spacerLeft.visibility = View.GONE
            }

            if (position == 7 - 1) {
                spacerRight.visibility = View.VISIBLE
            } else {
                spacerRight.visibility = View.GONE
            }

            photoImage.setImageResource(R.drawable.yamaga)
        }
    }
}