package com.pbernils.testehotmart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment

class LocationDetailsFragment : ToolbarFragment() {

    companion object {
        fun newInstance() = LocationDetailsFragment()
    }

    private lateinit var detailsViewModel: LocationDetailsViewModel
    private lateinit var photoAdapter: LocationDetailsPhotoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(LocationDetailsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_location_details, container, false)
//        val textView: TextView = root.findViewById(R.id.text_details)
//        detailsViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })

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

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(activity, 1, GridLayoutManager.HORIZONTAL, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_photos)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = photoAdapter
    }
}