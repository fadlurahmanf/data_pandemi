package com.fadlurahmanf.datapandemi.data.response.hospital

import com.google.gson.annotations.SerializedName

data class HospitalResponse(
    @SerializedName("hospitals")
    var listHospital:ArrayList<Hospital>?= arrayListOf()
){
    data class Hospital(
        @SerializedName("id")
        var id:String,
        @SerializedName("name")
        var hospitalName:String,
        @SerializedName("address")
        var address:String,
        @SerializedName("phone")
        var phone:String,
        @SerializedName("queue")
        var queue:Int,
        @SerializedName("bed_availability")
        var bed_availability:Int,
        @SerializedName("info")
        var info:String
    )
}
