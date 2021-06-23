package com.pbernils.testehotmart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _locationId = MutableLiveData<Int>()
    val locationId: LiveData<Int> = _locationId

    fun storeLocationId(id: Int) {
        _locationId.value = id
    }
}