package com.fadlurahmanf.datapandemi.ui.hospital.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.datapandemi.api.ServiceApiRumahSakit
import com.fadlurahmanf.datapandemi.base.BaseViewModel
import com.fadlurahmanf.datapandemi.data.repo.IntentRepository
import com.fadlurahmanf.datapandemi.data.response.hospital.DetailLocationHospitalResponse
import com.fadlurahmanf.datapandemi.data.response.hospital.HospitalResponse
import com.fadlurahmanf.datapandemi.extenson.uiSubscribe
import javax.inject.Inject

class SearchHospitalViewModel @Inject constructor(
    var intentRepo:IntentRepository,
    var serviceApiRumahSakit: ServiceApiRumahSakit
):BaseViewModel() {
    private var _listHospital = MutableLiveData<HospitalResponse>()
    var listHospital:LiveData<HospitalResponse> = _listHospital

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> = _isLoading

    private var _errorThrowable = MutableLiveData<String>()
    var errorThrowable:LiveData<String> = _errorThrowable

    private var _locationDetail = MutableLiveData<DetailLocationHospitalResponse.Data>()
    var locationDetail : LiveData<DetailLocationHospitalResponse.Data> = _locationDetail

    init {
        getAllHospital()
    }

    private fun getAllHospital() {
        _isLoading.postValue(true)
        addsubscription(serviceApiRumahSakit.getHospitalByProvinceIdAndCtyId(
            intentRepo?.searchHospitalIntentData?.idProvince?:"51prop",
            intentRepo?.searchHospitalIntentData?.idCity?:"5171",
            "1"
        ).uiSubscribe(
            { _listHospital.postValue(it)
                println("BERHASIL") },
            { _errorThrowable.postValue(it.message) },
            { _isLoading.postValue(false) }
        ))
    }

    fun getHospitalDetailLocation(id:String):LiveData<DetailLocationHospitalResponse.Data>{
        addsubscription(serviceApiRumahSakit.getHospitalLocationById(id?:"").uiSubscribe(
            {
                _locationDetail.postValue(it.data)
                println("BERHAIL ${it.data.name}")
            },
            { println("GAGAL ${it.message}")},
            { println("KOMPLIT") }
        ))
        return locationDetail
    }
}