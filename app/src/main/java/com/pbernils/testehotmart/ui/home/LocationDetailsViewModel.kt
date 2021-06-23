package com.pbernils.testehotmart.ui.home

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.google.gson.JsonParseException
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.api.LocationService
import com.pbernils.testehotmart.api.RetrofitHelper
import com.pbernils.testehotmart.model.LocationDetails
import com.pbernils.testehotmart.model.LocationList
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class LocationDetailsViewModel(application: Application) : AndroidViewModel(application) {

    private val locationService: LocationService = RetrofitHelper.getRetrofit().create(LocationService::class.java)

    private val _locationDetails = MutableLiveData<LocationDetails>()
    val locationDetails: LiveData<LocationDetails> = _locationDetails

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage:LiveData<String> = _errorMessage


    private suspend fun getLocationsDetails(id: Int) {

        try {

            val call = locationService.getLocationDetails(id)
            if (call.isSuccessful) {
                call.body()?.let {
                    _locationDetails.value = call.body()
                }
            } else {
                _errorMessage.value = getString(R.string.error_message_general)
            }

        } catch (e: UnknownHostException) {
            _errorMessage.value = getString(R.string.error_message_no_connection)
        } catch (e: JsonParseException) {
            _errorMessage.value = getString(R.string.error_message_general)
//        } catch (e: Exception) {
//            _errorMessage.value = getString(R.string.error_message_unexpected)
        }
    }

    fun fetchLocationDetails(id: Int) {
        viewModelScope.launch {
            getLocationsDetails(id)
        }
    }

    private fun getString(resId: Int): String {
        return getApplication<Application>().getString(resId)
    }
}