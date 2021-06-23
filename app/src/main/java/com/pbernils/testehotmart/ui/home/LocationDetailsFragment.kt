package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.pbernils.testehotmart.MainActivity
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment
import com.pbernils.testehotmart.model.LocationDetails
import com.pbernils.testehotmart.utils.RatingHelper

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

        val toolbar = root.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val height = setupToolbar(root)
        val gradient = root.findViewById<View>(R.id.gradient)
        val params = gradient.layoutParams
        params.height = params.height + height
        gradient.layoutParams = params

        photoAdapter = LocationDetailsPhotoAdapter()
        reviewAdapter = LocationDetailsReviewAdapter()

        fetchLocationDetails()

        root.findViewById<Button>(R.id.btn_reload).setOnClickListener {
            root.findViewById<View>(R.id.progress_circular).visibility = View.VISIBLE
            root.findViewById<View>(R.id.error_view).visibility = View.GONE
            fetchLocationDetails()
        }

        detailsViewModel.locationDetails.observe(viewLifecycleOwner, Observer {
            reloadView(root, it)
        })

        detailsViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            root.findViewById<View>(R.id.nested_scroll).visibility = View.GONE
            root.findViewById<View>(R.id.progress_circular).visibility = View.GONE
            root.findViewById<View>(R.id.error_view).visibility = View.VISIBLE
            val errorTextView = root.findViewById<TextView>(R.id.error_message)
            errorTextView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gridLayoutManager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
        val recyclerViewPhotos = view.findViewById<RecyclerView>(R.id.recycler_photos)
        recyclerViewPhotos.layoutManager = gridLayoutManager
        recyclerViewPhotos.adapter = photoAdapter

        val linearLayoutManager = LinearLayoutManager(activity)
        val recyclerViewReviews = view.findViewById<RecyclerView>(R.id.recycler_reviews)
        recyclerViewReviews.layoutManager = linearLayoutManager
        recyclerViewReviews.adapter = reviewAdapter
    }

    private fun fetchLocationDetails() {

        val main = activity as MainActivity
        main.getLocationId().observe(viewLifecycleOwner, Observer {
            detailsViewModel.fetchLocationDetails(it)
        })
    }

    private fun reloadView(root: View, locationDetails: LocationDetails) {

        root.findViewById<View>(R.id.progress_circular).visibility = View.GONE
        root.findViewById<View>(R.id.error_view).visibility = View.GONE
        root.findViewById<View>(R.id.nested_scroll).visibility = View.VISIBLE

        if (locationDetails.photo.isNullOrBlank()) {
            root.findViewById<AppBarLayout>(R.id.app_bar).setExpanded(false)
        }

        root.findViewById<TextView>(R.id.location_name).text = locationDetails.name

        val ratingValue = locationDetails.rating
        root.findViewById<TextView>(R.id.location_rating).text = "$ratingValue"

        val stars = listOf<ImageView>(
            root.findViewById(R.id.rating_1),
            root.findViewById(R.id.rating_2),
            root.findViewById(R.id.rating_3),
            root.findViewById(R.id.rating_4),
            root.findViewById(R.id.rating_5)
        )

        RatingHelper.displayRating(ratingValue.toInt(), stars)

        photoAdapter.data = locationDetails.photos
        photoAdapter.notifyDataSetChanged()

        if (photoAdapter.itemCount < 1) {
            root.findViewById<View>(R.id.label_photos).visibility = View.GONE
            root.findViewById<View>(R.id.recycler_photos).visibility = View.GONE
        }

        if (locationDetails.about.isNullOrBlank()) {
            root.findViewById<View>(R.id.label_about).visibility = View.GONE
            root.findViewById<View>(R.id.text_about).visibility = View.GONE
        } else {
            root.findViewById<TextView>(R.id.text_about).text = locationDetails.about
        }

        // Schedule
        val schedule = locationDetails.schedule
        schedule.nameDays(activity as Context)
        val string = schedule.getScheduleString(activity as Context)
        if (string.isNullOrBlank()) {
            root.findViewById<View>(R.id.icon_time).visibility = View.GONE
            root.findViewById<View>(R.id.text_schedule).visibility = View.GONE
        } else {
            root.findViewById<TextView>(R.id.text_schedule).text = string
        }

        if (locationDetails.phone.isNullOrBlank()) {
            root.findViewById<View>(R.id.icon_phone).visibility = View.GONE
            root.findViewById<View>(R.id.text_phone).visibility = View.GONE
        } else {
            root.findViewById<TextView>(R.id.text_phone).text = locationDetails.phone
        }

        if (locationDetails.address.isNullOrBlank()) {
            root.findViewById<View>(R.id.icon_pin).visibility = View.GONE
            root.findViewById<View>(R.id.text_address).visibility = View.GONE
        } else {
            root.findViewById<TextView>(R.id.text_address).text = locationDetails.address
        }

        reviewAdapter.data = locationDetails.reviews
        reviewAdapter.notifyDataSetChanged()

        if (reviewAdapter.itemCount < 1) {
            root.findViewById<View>(R.id.layout_reviews).visibility = View.GONE
        }
    }
}