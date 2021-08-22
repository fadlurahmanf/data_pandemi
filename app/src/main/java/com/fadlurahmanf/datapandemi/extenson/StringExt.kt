package com.fadlurahmanf.datapandemi.extenson

fun String.tigaAngkaBelakangKoma():String{
    var int = this.toInt()
    var resultString = "%,d".format(int)
    return resultString
}

fun String.convertBulanAngkaKeNama():String{
    var namaBulan = "Januari"
    if (this=="1") namaBulan = "Januari"
    else if (this=="2") namaBulan = "Februari"
    else if (this=="3") namaBulan = "Maret"
    else if (this=="4") namaBulan = "April"
    else if (this=="5") namaBulan = "Mei"
    else if (this=="6") namaBulan = "Juni"
    else if (this=="7") namaBulan = "Juli"
    else if (this=="8") namaBulan = "Agustus"
    else if (this=="9") namaBulan = "September"
    else if (this=="10") namaBulan = "Oktober"
    else if (this=="11") namaBulan = "November"
    else namaBulan = "Desember"
    return namaBulan
}

//[2021-08-01] -> FORMAT
fun String.convertCompletedDateToMonthAndDay():String{
    var completeDate:String = this.toString()
    var splitCompleteDate = completeDate.split("-")
    var month = splitCompleteDate[1].toInt().toString().convertBulanAngkaKeNama()
    var day = splitCompleteDate.last().toInt().toString()
    return "${day} ${month}" // Format 15 Juli
}

fun String.convertCompletedDateToMonthAndYear():String{
    var completeDate = this.toString()
    var splitCompleteDate = completeDate.split("-")
    var month = splitCompleteDate[1].toInt().toString().convertBulanAngkaKeNama()
    return "${month} ${splitCompleteDate.first()}"
}