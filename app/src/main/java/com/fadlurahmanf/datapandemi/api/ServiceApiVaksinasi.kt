package com.fadlurahmanf.datapandemi.api

import com.fadlurahmanf.datapandemi.data.response.VaksinasiResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ServiceApiVaksinasi {
    @GET("vaksin")
    fun getDataVaksinasi() : Observable<VaksinasiResponse>
}