package com.fadlurahmanf.datapandemi.api

import com.fadlurahmanf.datapandemi.data.response.city.CityHospitalResponse
import com.fadlurahmanf.datapandemi.data.response.hospital.DetailLocationHospitalResponse
import com.fadlurahmanf.datapandemi.data.response.hospital.HospitalResponse
import com.fadlurahmanf.datapandemi.data.response.province.GetProvinceIdResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApiRumahSakit {
    @GET("get-provinces")
    fun getProvince():Observable<GetProvinceIdResponse>
    @GET("get-cities")
    fun getProvinceById(
        @Query("provinceid") id:String
    ) : Observable<CityHospitalResponse>
    @GET("get-hospitals")
    fun getHospitalByProvinceIdAndCtyId(
        @Query("provinceid") provinceid:String,
        @Query("cityid") cityid:String,
        @Query("type") type:String
    ) : Observable<HospitalResponse>
    @GET("get-hospital-map")
    fun getHospitalLocationById(
        @Query("hospitalid") id: String
    ) : Observable<DetailLocationHospitalResponse>
}