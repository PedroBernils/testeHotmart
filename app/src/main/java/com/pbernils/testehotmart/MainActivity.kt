package com.pbernils.testehotmart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pbernils.testehotmart.model.Location
import com.pbernils.testehotmart.utils.Misc
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val navView: BottomNavigationView = nav_view

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_map, R.id.navigation_profile
            )
        )

        val height = Misc.getNavigationBarHeight(this)
        val navColor = navigation_bar_color
        val params = navColor.layoutParams
        params.height = height
        navColor.layoutParams = params

        navView.setupWithNavController(navController)
        navView.itemIconTintList = null
    }

    fun storeLocation(location: Location) {
        mainViewModel.storeLocation(location)
    }

    fun getLocationId(): LiveData<Location> {
        return mainViewModel.location
    }
}