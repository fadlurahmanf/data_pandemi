package com.fadlurahmanf.datapandemi.ui.hospital.province

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.datapandemi.api.ServiceApiRumahSakit
import com.fadlurahmanf.datapandemi.base.BaseViewModel
import com.fadlurahmanf.datapandemi.data.response.province.GetProvinceIdResponse
import com.fadlurahmanf.datapandemi.extenson.uiSubscribe
import javax.inject.Inject

class SearchProvinceHospitalViewModel @Inject constructor(
    var serviceApiRumahSakit: ServiceApiRumahSakit
):BaseViewModel() {

    private var _listAllProvince = MutableLiveData<GetProvinceIdResponse>()
    var listAllProvince:LiveData<GetProvinceIdResponse> = _listAllProvince

    private var _isLoading = MutableLiveData<Boolean>()
    var isLoading:LiveData<Boolean> = _isLoading

    private var _errorThrowable = MutableLiveData<String>()
    var errorThrowable:LiveData<String> = _errorThrowable

    init {
        getAllProvince()
    }

    private fun getAllProvince() {
        _isLoading.postValue(true)
        addsubscription(serviceApiRumahSakit.getProvince().uiSubscribe(
            { _listAllProvince.postValue(it) },
            { _errorThrowable.postValue(it.message) },
            { _isLoading.postValue(false) }
        ))
    }


}