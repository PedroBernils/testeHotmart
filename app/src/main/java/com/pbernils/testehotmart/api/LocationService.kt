package com.pbernils.testehotmart.api

import com.pbernils.testehotmart.model.LocationList
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface LocationService {

    @GET("/locations")
    suspend fun getLocationList(): Response<LocationList>
}