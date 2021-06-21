package com.pbernils.testehotmart.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Home"
    }

    val locationList = ArrayList<String>()

    val text: LiveData<String> = _text

    fun changeText(text: String) {
        _text.value = text
    }
}