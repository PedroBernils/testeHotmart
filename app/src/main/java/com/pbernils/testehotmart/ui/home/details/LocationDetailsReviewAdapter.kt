package com.pbernils.testehotmart.ui.home.details

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.model.Review
import com.pbernils.testehotmart.utils.Misc
import com.pbernils.testehotmart.utils.RatingHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_location_details_review_cell.view.*

class LocationDetailsReviewAdapter(
    val context: Context
): RecyclerView.Adapter<LocationDetailsReviewAdapter.ReviewHolder>() {

    var data: ArrayList<Review>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_location_details_review_cell, parent, false)
        return ReviewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return when (data?.size) {
            null -> 0
            0,1,2 -> data?.size ?: 0
            else -> 3
        }
    }

    inner class ReviewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        private val stars = listOf(
            itemView.rating_1,
            itemView.rating_2,
            itemView.rating_3,
            itemView.rating_4,
            itemView.rating_5
        )

        fun bind() {

            data?.let{

                val review = it[adapterPosition]

                val color = Misc.getRandomAvatarColor(context)

                if (review.authorAvatar.isNotBlank()) {
                    val colorDrawable = ColorDrawable(color)
                    itemView.initials.visibility = View.GONE
                    Picasso.get()
                        .load(review.authorAvatar)
                        .placeholder(colorDrawable)
                        .error(colorDrawable)
                        .into(itemView.avatar)
                } else {
                    itemView.avatar.setBackgroundColor(color)
                    itemView.initials.visibility = View.VISIBLE
                    itemView.initials.text = review.getInitials()
                }

                RatingHelper.displayRating(review.rating.toInt(), stars)

                itemView.review_title.text = review.title
                itemView.review_body.text = review.body
                itemView.review_author.text = "${review.authorName}, ${review.location}"
            }
        }
    }
}