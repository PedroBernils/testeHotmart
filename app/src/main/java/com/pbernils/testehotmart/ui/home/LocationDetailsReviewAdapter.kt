package com.pbernils.testehotmart.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R

class LocationDetailsReviewAdapter(): RecyclerView.Adapter<LocationDetailsReviewAdapter.ReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_details_review_cell, parent, false)
        return ReviewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 3
    }

    inner class ReviewHolder(item: View): RecyclerView.ViewHolder(item) {

        fun bind() {

        }
    }
}