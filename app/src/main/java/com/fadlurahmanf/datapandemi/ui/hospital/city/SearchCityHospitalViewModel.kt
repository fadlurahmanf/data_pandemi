package com.fadlurahmanf.datapandemi.ui.hospital.city

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.datapandemi.api.ServiceApiRumahSakit
import com.fadlurahmanf.datapandemi.base.BaseViewModel
import com.fadlurahmanf.datapandemi.data.repo.IntentRepository
import com.fadlurahmanf.datapandemi.data.repo.ProvinceRepository
import com.fadlurahmanf.datapandemi.data.response.city.CityHospitalResponse
import com.fadlurahmanf.datapandemi.extenson.uiSubscribe
import javax.inject.Inject

class SearchCityHospitalViewModel @Inject constructor(
    var intentRepository: IntentRepository,
    var serviceApiRumahSakit: ServiceApiRumahSakit
):BaseViewModel() {

    private var _listCity = MutableLiveData<CityHospitalResponse>()
    var listCity:LiveData<CityHospitalResponse> = _listCity

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> = _isLoading

    private var _errorThrowable = MutableLiveData<String>()
    var errorThrowable:LiveData<String> = _errorThrowable

    init {
        getAllCity()
    }

    private fun getAllCity() {
        _isLoading.postValue(true)
        addsubscription(serviceApiRumahSakit.getProvinceById(intentRepository?.searchHospitalIntentData?.idProvince?:"51prop").uiSubscribe(
            { _listCity.postValue(it) },
            { _errorThrowable.postValue(it.message) },
            { _isLoading.postValue(false) }
        ))
    }
}