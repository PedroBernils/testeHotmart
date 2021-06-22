package com.pbernils.testehotmart.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.ActivityNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment
import com.pbernils.testehotmart.utils.Misc

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
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
//
//        root.findViewById<Button>(R.id.btn_something).setOnClickListener {
//            homeViewModel.changeText("Something")
//        }

        setupToolbar(root)

        locationAdapter = HomeLocationAdapter(activity as FragmentActivity, homeViewModel.locationList) {
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity as AppCompatActivity,
                Pair.create(it, "photo"))
            val extras = ActivityNavigatorExtras(options)

            findNavController().navigate(
                R.id.action_home_to_details,
                null,
                null,
                extras)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val locationManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = locationManager
        recyclerView.adapter = locationAdapter
    }
}