package com.pbernils.testehotmart

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pbernils.testehotmart.utils.Misc


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_profile
            )
        )

//        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
//        toolbar.setNavigationIconTint(Color.WHITE)
//        toolbar.navigationIcon?.colorFilter = BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
//            ContextCompat.getColor(this, R.color.red), BlendModeCompat.SRC_ATOP)
//
        navView.setupWithNavController(navController)
        navView.itemIconTintList = null
    }

    /**
     * Get the height of the status bar
     * @return
     */
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    //Set the height of the layout from the status bar
    fun setLayoutPadding(activity: Activity?, drawerLayout: DrawerLayout) {
        val contentLayout = drawerLayout.getChildAt(0) as ViewGroup
        contentLayout.getChildAt(1)
            .setPadding(
                contentLayout.paddingLeft, getStatusBarHeight() + contentLayout.paddingTop,
                contentLayout.paddingRight, contentLayout.paddingBottom
            )
    }

}