package com.fadlurahmanf.datapandemi.extenson

import java.sql.Timestamp

fun Timestamp.toMonth():String{
    var justDate :String = this.toString().split(" ")[0]
    return justDate.split("-")[1]
}