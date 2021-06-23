package com.pbernils.testehotmart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pbernils.testehotmart.model.Location

class MainViewModel: ViewModel() {

    private val _location = MutableLiveData<Location>()
    val location: LiveData<Location> = _location

    fun storeLocation(location: Location) {
        _location.value = location
    }
}