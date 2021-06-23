package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.pbernils.testehotmart.MainActivity
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment
import com.pbernils.testehotmart.model.LocationDetails
import com.pbernils.testehotmart.utils.Misc
import com.pbernils.testehotmart.utils.RatingHelper
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_location_details.view.*
import kotlinx.android.synthetic.main.fragment_location_details_body.view.*
import kotlinx.android.synthetic.main.fragment_location_details_header.view.*
import kotlinx.android.synthetic.main.fragment_location_details_reviews.view.*
import kotlinx.android.synthetic.main.item_error_view.view.*

class LocationDetailsFragment : ToolbarFragment() {

    companion object {
        fun newInstance() = LocationDetailsFragment()
    }

    private lateinit var detailsViewModel: LocationDetailsViewModel
    private lateinit var photoAdapter: LocationDetailsPhotoAdapter
    private lateinit var reviewAdapter: LocationDetailsReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel = ViewModelProvider(this).get(LocationDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_location_details, container, false)

        val toolbar = root.toolbar
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val height = setupToolbar(root)
        val gradient = root.gradient
        val params = gradient.layoutParams
        params.height = params.height + height
        gradient.layoutParams = params

        photoAdapter = LocationDetailsPhotoAdapter(activity as Context)
        reviewAdapter = LocationDetailsReviewAdapter()

        fetchLocationDetails(root)

        root.btn_reload.setOnClickListener {
            root.progress_circular.visibility = View.VISIBLE
            root.error_view.visibility = View.GONE
            fetchLocationDetails(root)
        }

        detailsViewModel.locationDetails.observe(viewLifecycleOwner, Observer {
            reloadView(root, it)
        })

        detailsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            root.nested_scroll.visibility = View.GONE
            root.progress_circular.visibility = View.GONE
            root.error_view.visibility = View.VISIBLE
            root.error_message.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
        val recyclerViewPhotos = view.recycler_photos
        recyclerViewPhotos.layoutManager = gridLayoutManager
        recyclerViewPhotos.adapter = photoAdapter

        val linearLayoutManager = LinearLayoutManager(activity)
        val recyclerViewReviews = view.recycler_reviews
        recyclerViewReviews.layoutManager = linearLayoutManager
        recyclerViewReviews.adapter = reviewAdapter
    }

    private fun fetchLocationDetails(root: View) {

        val main = activity as MainActivity
        main.getLocationId().observe(viewLifecycleOwner, Observer {
            detailsViewModel.fetchLocationDetails(it.id)
            val color = Misc.getRandomColor(main)
            if (it.photo.isNullOrBlank()) {
                root.detail_image.setBackgroundColor(color)
            } else {
                val colorDrawable = ColorDrawable(color)
                Picasso.get()
                    .load(it.photo)
                    .placeholder(colorDrawable)
                    .error(colorDrawable)
                    .into(root.detail_image)
            }
        })
    }

    private fun reloadView(root: View, locationDetails: LocationDetails) {

        root.progress_circular.visibility = View.GONE
        root.error_view.visibility = View.GONE
        root.nested_scroll.visibility = View.VISIBLE

        root.location_name.text = locationDetails.name

        val ratingValue = locationDetails.rating
        root.location_rating.text = "$ratingValue"

        val stars = listOf<ImageView>(
            root.rating_1,
            root.rating_2,
            root.rating_3,
            root.rating_4,
            root.rating_5
        )

        RatingHelper.displayRating(ratingValue.toInt(), stars)

        locationDetails.grabPhotos()

        photoAdapter.data = locationDetails.photos
        photoAdapter.notifyDataSetChanged()

        if (photoAdapter.itemCount < 1) {
            root.label_photos.visibility = View.GONE
            root.recycler_photos.visibility = View.GONE
        }

        if (locationDetails.about.isNullOrBlank()) {
            root.label_about.visibility = View.GONE
            root.text_about.visibility = View.GONE
        } else {
            root.text_about.text = locationDetails.about
        }

        // Schedule
        val schedule = locationDetails.schedule
        schedule.nameDays(activity as Context)
        val string = schedule.getScheduleString(activity as Context)
        if (string.isNullOrBlank()) {
            root.icon_time.visibility = View.GONE
            root.text_schedule.visibility = View.GONE
        } else {
            root.text_schedule.text = string
        }

        if (locationDetails.phone.isNullOrBlank()) {
            root.icon_phone.visibility = View.GONE
            root.text_phone.visibility = View.GONE
        } else {
            root.text_phone.text = locationDetails.phone
        }

        if (locationDetails.address.isNullOrBlank()) {
            root.icon_pin.visibility = View.GONE
            root.text_address.visibility = View.GONE
        } else {
            root.text_address.text = locationDetails.address
        }

        reviewAdapter.data = locationDetails.reviews
        reviewAdapter.notifyDataSetChanged()

        if (reviewAdapter.itemCount < 1) {
            root.layout_reviews.visibility = View.GONE
        }
    }
}