package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.utils.Misc
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_location_details_photo_cell.view.*

class LocationDetailsPhotoAdapter(
    private val context: Context
): RecyclerView.Adapter<LocationDetailsPhotoAdapter.PhotoHolder>() {

    var data: List<String>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_details_photo_cell, parent, false)
        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    inner class PhotoHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind() {

            data?.let {
                if (adapterPosition == 0) {
                    itemView.spacer_left.visibility = View.VISIBLE
                } else {
                    itemView.spacer_left.visibility = View.GONE
                }

                if (adapterPosition == it.size - 1) {
                    itemView.spacer_right.visibility = View.VISIBLE
                } else {
                    itemView.spacer_right.visibility = View.GONE
                }

                val photo = it[adapterPosition]
                val color = Misc.getRandomColor(context)

                if (photo.isNullOrBlank()) {
                    itemView.photo.setBackgroundColor(color)
                } else {
                    val colorDrawable = ColorDrawable(color)
                    Picasso.get()
                        .load(photo)
                        .placeholder(colorDrawable)
                        .error(colorDrawable)
                        .into(itemView.photo)
                }
            }
        }
    }
}