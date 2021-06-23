package com.pbernils.testehotmart.ui.profile

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
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : ToolbarFragment() {

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        profileViewModel =
                ViewModelProvider(this).get(ProfileViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_profile, container, false)
        val toolbar = root.toolbar
        toolbar.title = getString(R.string.title_profile)

        setupToolbar(root)

        val textView: TextView = root.text_notifications
        profileViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}