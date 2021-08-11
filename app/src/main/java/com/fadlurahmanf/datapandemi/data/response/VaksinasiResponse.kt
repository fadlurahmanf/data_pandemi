package com.fadlurahmanf.datapandemi.data.response

import com.google.gson.annotations.SerializedName

data class VaksinasiResponse(
    @SerializedName("totalsasaran")
    var totalSasaran:Int,
    @SerializedName("vaksinasi1")
    var vaksinasi1:Int,
    @SerializedName("vaksinasi2")
    var vaksinasi2:Int,
    @SerializedName("lastUpdate")
    var lastUpdate:String
)
