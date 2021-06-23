package com.pbernils.testehotmart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pbernils.testehotmart.MainActivity
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.item_error_view.view.*

class HomeFragment : ToolbarFragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var locationAdapter: HomeLocationAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        setupToolbar(root)

        locationAdapter = HomeLocationAdapter(activity as FragmentActivity) {

            val location = locationAdapter.data?.get(it)
            location?.let {

                val main = activity as MainActivity
                main.storeLocation(it)

                findNavController().navigate(
                    R.id.action_home_to_details,
                    null,
                    null,
                    null
                )
            }
        }

        homeViewModel.fetchLocationList()

        root.btn_reload.setOnClickListener {
            root.progress_circular.visibility = View.VISIBLE
            root.error_view.visibility = View.GONE
            homeViewModel.fetchLocationList()
        }

        homeViewModel.locationList.observe(viewLifecycleOwner, Observer {
            locationAdapter.data = it.locationList
            locationAdapter.notifyDataSetChanged()
            root.progress_circular.visibility = View.GONE
            root.error_view.visibility = View.GONE
            root.recycler_view.visibility = View.VISIBLE
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            root.recycler_view.visibility = View.GONE
            root.progress_circular.visibility = View.GONE
            root.error_view.visibility = View.VISIBLE
            root.error_message.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recyclerView = view.recycler_view
        recyclerView.layoutManager = locationManager
        recyclerView.adapter = locationAdapter
    }
}