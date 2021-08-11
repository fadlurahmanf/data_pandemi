package com.fadlurahmanf.datapandemi.api

import com.fadlurahmanf.datapandemi.data.response.province.DataPandemiPerProvinsiResponse
import com.fadlurahmanf.datapandemi.data.response.UpdateDataPandemiPemerintahRespone
import io.reactivex.Observable
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ServicePemerintah {
    @GET("api/update.json")
    fun getDataUpdate():Observable<UpdateDataPandemiPemerintahRespone>
    @GET("api/prov.json")
    fun getDataPerProvince():Observable<DataPandemiPerProvinsiResponse>
}