package com.pbernils.testehotmart.custom

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.utils.Misc

abstract class ToolbarFragment: Fragment() {

    fun setupToolbar(root: View): Int {

        val height = Misc.getStatusBarHeight(activity as Context)
        val toolbar = root.findViewById<MaterialToolbar>(R.id.toolbar)
        val params = toolbar.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, height, 0, 0)
        toolbar.layoutParams = params
        return height
    }
}