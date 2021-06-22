package com.pbernils.testehotmart.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pbernils.testehotmart.R

class LocationDetailsViewModel(application: Application) : AndroidViewModel(application) {
    private val _text = MutableLiveData<String>().apply {
        value = getApplication<Application>().applicationContext.getString(R.string.title_activity_location_details)
    }
    val text: LiveData<String> = _text
}