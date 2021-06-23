package com.pbernils.testehotmart.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pbernils.testehotmart.MainActivity
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment

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
                main.storeLocationId(location.id)

                findNavController().navigate(
                    R.id.action_home_to_details,
                    null,
                    null,
                    null
                )
            }
        }

        root.findViewById<Button>(R.id.btn_reload).setOnClickListener {
            root.findViewById<View>(R.id.progress_circular).visibility = View.VISIBLE
            root.findViewById<View>(R.id.error_view).visibility = View.GONE
            homeViewModel.fetchLocationList()
        }

        homeViewModel.locationList.observe(viewLifecycleOwner, Observer {
            locationAdapter.data = it.locationList
            locationAdapter.notifyDataSetChanged()
            root.findViewById<View>(R.id.progress_circular).visibility = View.GONE
            root.findViewById<View>(R.id.error_view).visibility = View.GONE
            root.findViewById<View>(R.id.recycler_view).visibility = View.VISIBLE
        })

        homeViewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            root.findViewById<View>(R.id.recycler_view).visibility = View.GONE
            root.findViewById<View>(R.id.progress_circular).visibility = View.GONE
            root.findViewById<View>(R.id.error_view).visibility = View.VISIBLE
            val errorTextView = root.findViewById<TextView>(R.id.error_message)
            errorTextView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = locationManager
        recyclerView.adapter = locationAdapter

        homeViewModel.fetchLocationList()
    }
}