package com.pbernils.testehotmart.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.MaterialToolbar
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.custom.ToolbarFragment
import kotlinx.android.synthetic.main.fragment_map.view.*

class MapFragment : ToolbarFragment() {

    private lateinit var mapViewModel: MapViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        mapViewModel =
                ViewModelProvider(this).get(MapViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_map, container, false)
        val toolbar = root.toolbar
        toolbar.title = getString(R.string.title_map)

        setupToolbar(root)

        val textView: TextView = root.text_map
        mapViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}