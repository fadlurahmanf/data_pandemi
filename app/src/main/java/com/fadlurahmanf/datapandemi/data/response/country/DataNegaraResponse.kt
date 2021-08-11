package com.fadlurahmanf.datapandemi.data.response.country

import com.google.gson.annotations.SerializedName

data class DataNegaraResponse(
    @SerializedName("attributes")
    var attributes:Attributes
){
    data class Attributes(
        @SerializedName("Country_Region")
        var country:String,
        @SerializedName("Lat")
        var lat:Double,
        @SerializedName("Long_")
        var long:Double,
        @SerializedName("Confirmed")
        var total_case_confirmed:Int,
        @SerializedName("Deaths")
        var total_case_deaths:Int
    )
}
