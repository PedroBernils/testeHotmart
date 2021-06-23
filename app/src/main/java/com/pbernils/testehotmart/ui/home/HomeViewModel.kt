package com.pbernils.testehotmart.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.google.gson.JsonParseException
import com.pbernils.testehotmart.R
import com.pbernils.testehotmart.api.LocationService
import com.pbernils.testehotmart.api.RetrofitHelper
import com.pbernils.testehotmart.model.LocationList
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val locationService: LocationService = RetrofitHelper.getRetrofit().create(LocationService::class.java)

    private val _locationList = MutableLiveData<LocationList>()
    val locationList: LiveData<LocationList> = _locationList

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage:LiveData<String> = _errorMessage

    private suspend fun getLocationsList() {

        try {

            val call = locationService.getLocationList()
            if (call.isSuccessful) {
                call.body()?.let {
                    _locationList.value = call.body()
                }
            } else {
                _errorMessage.value = getString(R.string.error_message_general)
            }

        } catch (e: UnknownHostException) {
            _errorMessage.value = getString(R.string.error_message_no_connection)
        } catch (e: JsonParseException) {
            _errorMessage.value = getString(R.string.error_message_general)
        } catch (e: Exception) {
            _errorMessage.value = getString(R.string.error_message_unexpected)
        }
    }

    fun fetchLocationList() {
        viewModelScope.launch {
            getLocationsList()
        }
    }

    private fun getString(resId: Int): String {
        return getApplication<Application>().getString(resId)
    }
}