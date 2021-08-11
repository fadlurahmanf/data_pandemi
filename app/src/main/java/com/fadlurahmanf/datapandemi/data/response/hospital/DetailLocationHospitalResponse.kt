package com.fadlurahmanf.datapandemi.data.response.hospital

import com.google.gson.annotations.SerializedName

data class DetailLocationHospitalResponse(
    @SerializedName("data")
    var data:Data
){
    data class Data(
        @SerializedName("id")
        var id:String,
        @SerializedName("name")
        var name:String,
        @SerializedName("lat")
        var lat:String,
        @SerializedName("long")
        var lng:String,
        @SerializedName("gmaps")
        var gmaps:String
    )
}
