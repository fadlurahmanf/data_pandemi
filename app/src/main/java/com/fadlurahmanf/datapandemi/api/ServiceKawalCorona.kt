package com.fadlurahmanf.datapandemi.api

import com.fadlurahmanf.datapandemi.data.response.country.DataNegaraResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface ServiceKawalCorona {
    @GET("/")
    fun getListDataAllCountry():Observable<List<DataNegaraResponse>>
}