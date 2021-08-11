package com.fadlurahmanf.datapandemi.data

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class DataProvinsi(
    var id: String?,
    var namaProvinsi: String?,
    var totalKasus:TotalKasus,
    var penambahan:Penambahan,
    var jenisKelamin:JenisKelamin?= null,
    var kelompokUmur:KelompokUmur?=null
){
    data class TotalKasus(
        var jumlahKasus:Int,
        var jumlahSembuh:Int,
        var jumlahMeninggal:Int,
        var jumlahDiRawat:Int,
    )
    data class Penambahan(
        var positif:Int,
        var sembuh:Int,
        var meninggal:Int
    )
    data class JenisKelamin(
        var pria:Int?=0,
        var wanita:Int?=0
    )
    data class KelompokUmur(
        var nol_sd_5:Int?=0,
        var enam_sd_18:Int?=0,
        var sembilanBelas_sd_30:Int?=0,
        var tigaSatu_sd_45:Int?=0,
        var empatEnam_sd_59:Int?=0,
        var lebih_dari_60:Int?=0
    )
}
