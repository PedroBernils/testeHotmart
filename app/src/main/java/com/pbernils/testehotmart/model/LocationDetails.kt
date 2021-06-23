package com.pbernils.testehotmart.model

import com.google.gson.annotations.SerializedName

data class LocationDetails(
    @SerializedName("id") val id: Int,
    val photo: String,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("review") val rating: Float,
    @SerializedName("about") val about: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("adress") val address: String,
    @SerializedName("schedule") val schedule: Schedule,
    val photos: List<String>,
    val reviews: List<Review>
)

data class Schedule(
    @SerializedName("sunday") val sunday: WorkingHours,
    @SerializedName("monday") val monday: WorkingHours,
    @SerializedName("tuesday") val tuesday: WorkingHours,
    @SerializedName("wednesday") val wednesday: WorkingHours,
    @SerializedName("thursday") val thursday: WorkingHours,
    @SerializedName("friday") val friday: WorkingHours,
    @SerializedName("saturday") val saturday: WorkingHours
)

data class WorkingHours(
    @SerializedName("open") val open: String,
    @SerializedName("close") val close: String
)

data class Review(
    val title: String,
    val body: String,
    val rating: Float,
    val authorName: String,
    val authorAvatar: String
)
