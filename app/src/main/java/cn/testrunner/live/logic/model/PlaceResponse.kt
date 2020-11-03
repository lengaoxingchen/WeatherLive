package cn.testrunner.live.logic.model

import android.location.Location
import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String, val places: List<Place>)


data class Place(val name: String, val location: Location, @SerializedName("formatted_address") val address: String)
