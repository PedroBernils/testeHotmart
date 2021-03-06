package com.pbernils.testehotmart.model

import com.google.gson.annotations.SerializedName
import com.pbernils.testehotmart.utils.Misc

data class LocationList(
    @SerializedName("listLocations") val locationList: List<Location>
)

data class Location(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("review") val rating: Float,
    @SerializedName("type") val type: String,
    var photo: String? = null
) {

    fun grabPhoto() {
        photo = Misc.getRandomImageUrl()
    }
}