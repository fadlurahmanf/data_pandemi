package com.fadlurahmanf.datapandemi.data.response.province

import com.google.gson.annotations.SerializedName

data class DataPandemiPerProvinsiResponse(
    @SerializedName("last_date")
    var last_date:String,
    @SerializedName("list_data")
    var list_data:ArrayList<DataPerProvinsi>?= arrayListOf()
){
    data class DataPerProvinsi(
        @SerializedName("key")
        var provinsi:String,
        @SerializedName("jumlah_kasus")
        var jumlah_kasus:Int,
        @SerializedName("jumlah_sembuh")
        var jumlah_sembuh:Int,
        @SerializedName("jumlah_meninggal")
        var jumlah_meninggal:Int,
        @SerializedName("jumlah_dirawat")
        var jumlah_dirawat:Int,
        @SerializedName("jenis_kelamin")
        var jenis_kelamin:ArrayList<Kelamin>?= arrayListOf(),
        @SerializedName("kelompok_umur")
        var kelompok_umur:ArrayList<Kel_Umur>?= arrayListOf(),
        @SerializedName("lokasi")
        var lokasi: Lokasi,
        @SerializedName("penambahan")
        var penambahan: Penambahan
    ){
        data class Kelamin(
            @SerializedName("key")
            var kelamin:String,
            @SerializedName("doc_count")
            var jumlah:Int
        )
        data class Kel_Umur(
            @SerializedName("key")
            var key:String,
            @SerializedName("doc_count")
            var jumlah:Int,
        )
        data class Lokasi(
            @SerializedName("lat")
            var lat:Double,
            @SerializedName("lon")
            var lon:Double
        )
        data class Penambahan(
            @SerializedName("positif")
            var positif:Int,
            @SerializedName("sembuh")
            var sembuh:Int,
            @SerializedName("meninggal")
            var meninggal:Int
        )
    }
}
