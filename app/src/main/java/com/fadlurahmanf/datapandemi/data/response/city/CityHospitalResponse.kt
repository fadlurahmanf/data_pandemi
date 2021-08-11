package com.fadlurahmanf.datapandemi.data.response.city

import com.google.gson.annotations.SerializedName

data class CityHospitalResponse(
    @SerializedName("cities")
    var listCity:ArrayList<City>?= arrayListOf()
){
    data class City(
        @SerializedName("id")
        var id:String,
        @SerializedName("name")
        var name:String
    )
}
