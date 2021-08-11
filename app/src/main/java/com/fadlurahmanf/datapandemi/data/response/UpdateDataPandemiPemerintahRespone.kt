package com.fadlurahmanf.datapandemi.data.response

import com.google.gson.annotations.SerializedName

data class UpdateDataPandemiPemerintahRespone(
    @SerializedName("data")
    var dataTotal: DataTotal,
    @SerializedName("update")
    var updateToday: UpdateToday
){
    data class DataTotal(
        @SerializedName("id")
        var id:Int,
        @SerializedName("jumlah_odp")
        var jumlah_odp:Int,
        @SerializedName("total_spesimen")
        var jumlah_positif:Int,
        @SerializedName("total_spesimen_negatif")
        var jumlah_negatif:Int
    )
    data class UpdateToday(
        @SerializedName("penambahan")
        var penambahan: Penambahan,
        @SerializedName("harian")
        var listharian:ArrayList<Harian>
    ){
        data class Penambahan(
            @SerializedName("jumlah_positif")
            var jumlah_positif:Int,
            @SerializedName("jumlah_meninggal")
            var jumlah_meninggal:Int,
            @SerializedName("jumlah_sembuh")
            var jumlah_sembuh:Int,
            @SerializedName("jumlah_dirawat")
            var jumlah_dirawat:Int,
            @SerializedName("tanggal")
            var tanggal_update:String,
            @SerializedName("created")
            var created:String
        )
        data class Harian(
            @SerializedName("key_as_string")
            var keyAsString: String,
            @SerializedName("jumlah_meninggal")
            var jumlahMeninggal: Jumlah,
            @SerializedName("jumlah_sembuh")
            var jumlahSembuh: Jumlah,
            @SerializedName("jumlah_positif")
            var jumlahPositif: Jumlah,
            @SerializedName("jumlah_dirawat")
            var jumlahDirawat: Jumlah,
            @SerializedName("jumlah_positif_kum")
            var jumlahPositifKum: Jumlah,
            @SerializedName("jumlah_sembuh_kum")
            var jumlahSembuhKum: Jumlah,
            @SerializedName("jumlah_meninggal_kum")
            var jumlahMeninggalKum: Jumlah,
            @SerializedName("jumlah_dirawat_kum")
            var jumlahDirawatKum: Jumlah
        ){
            data class Jumlah(
                @SerializedName("value")
                var value:Int
            )
        }
    }
}
