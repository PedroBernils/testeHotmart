package com.pbernils.testehotmart.model

import android.content.Context
import com.google.gson.annotations.SerializedName
import com.pbernils.testehotmart.R

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
    @SerializedName("sunday") val sunday: WorkingHours?,
    @SerializedName("monday") val monday: WorkingHours?,
    @SerializedName("tuesday") val tuesday: WorkingHours?,
    @SerializedName("wednesday") val wednesday: WorkingHours?,
    @SerializedName("thursday") val thursday: WorkingHours?,
    @SerializedName("friday") val friday: WorkingHours?,
    @SerializedName("saturday") val saturday: WorkingHours?
) {

    fun nameDays(context: Context) {

        sunday?.day = context.getString(R.string.sunday)
        monday?.day = context.getString(R.string.monday)
        tuesday?.day = context.getString(R.string.tuesday)
        wednesday?.day = context.getString(R.string.wednesday)
        thursday?.day = context.getString(R.string.thursday)
        friday?.day = context.getString(R.string.friday)
        saturday?.day = context.getString(R.string.saturday)
    }

    fun getScheduleString(context: Context): String {

        var text = ""

        var hours = ArrayList<WorkingHours>()

        sunday?.let { hours.add(it) }
        monday?.let { hours.add(it) }
        tuesday?.let { hours.add(it) }
        wednesday?.let { hours.add(it) }
        thursday?.let { hours.add(it) }
        friday?.let { hours.add(it) }
        saturday?.let { hours.add(it) }

        val groups = hours.groupBy { it.toCompare() }

        val keys = groups.keys

        if (keys.isEmpty()) {
            return ""
        }

        if (keys.size == 1) {
            sunday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
            monday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
            tuesday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
            wednesday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
            thursday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
            friday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
            saturday?.let { return context.getString(R.string.text_all_days, it.open, it.close) }
        }

        for (key in keys) {
            groups[key]?.let {
                when(it.size) {
                    1 -> {
                        val workingHours = it[0]
                        val string = context.getString(R.string.text_solo_day, workingHours.day, workingHours.open, workingHours.close)
                        text = "$text$string\n"
                    }
                    2 -> {
                        val workingHours1 = it[0]
                        val workingHours2 = it[1]
                        val string = context.getString(R.string.text_day_and_day, workingHours1.day, workingHours2.day, workingHours1.open, workingHours1.close)
                        text = "$text$string\n"
                    }
                    else -> {
                        var first = ""
                        var last = ""
                        for (i in it.size - 1 downTo 0) {
                            if (i == it.size - 1) {
                                last = it[i].day
                            }

                            if (i == 0) {
                                first = it[i].day
                            }
                        }

                        val string = context.getString(R.string.text_day_to_day, first, last, it[0].open, it[0].close)
                        text = "$text$string\n"
                    }
                }
            }
        }

        if (text.isNotBlank()) {
            text = text.substring(0, text.length - 2)
        }

        return text
    }
}

data class WorkingHours(
    @SerializedName("open") val open: String,
    @SerializedName("close") val close: String
) {

    var day: String = ""

    data class Compare(
        val open: String,
        val close: String
    )

    fun toCompare() = Compare(open, close)
}

data class Review(
    val title: String,
    val body: String,
    val rating: Float,
    val authorName: String,
    val authorAvatar: String
)
