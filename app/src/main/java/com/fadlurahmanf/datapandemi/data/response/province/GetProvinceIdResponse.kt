package com.fadlurahmanf.datapandemi.data.response.province

import com.google.gson.annotations.SerializedName

data class GetProvinceIdResponse(
    @SerializedName("provinces")
    var listProvince:ArrayList<Province>?= arrayListOf()
){
    data class Province(
        @SerializedName("id")
        var id:String,
        @SerializedName("name")
        var nameProvince:String
    )
}
