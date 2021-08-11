package com.fadlurahmanf.datapandemi.ui

import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.datapandemi.api.ServiceApiVaksinasi
import com.fadlurahmanf.datapandemi.api.ServicePemerintah
import com.fadlurahmanf.datapandemi.base.BaseViewModel
import com.fadlurahmanf.datapandemi.data.response.province.DataPandemiPerProvinsiResponse
import com.fadlurahmanf.datapandemi.data.response.UpdateDataPandemiPemerintahRespone
import com.fadlurahmanf.datapandemi.data.response.VaksinasiResponse
import com.fadlurahmanf.datapandemi.extenson.uiSubscribe
import javax.inject.Inject

class MainViewModel @Inject constructor(
    var servicePemerintah: ServicePemerintah,
    var serviceApiVaksinasi: ServiceApiVaksinasi
):BaseViewModel() {

    private var _dataPandemiUpdatePemerintah = MutableLiveData<UpdateDataPandemiPemerintahRespone>()
    var dataPandemiUpdatePemerintah = _dataPandemiUpdatePemerintah

    private var _dataPerProvinsi = MutableLiveData<DataPandemiPerProvinsiResponse>()
    var dataPerProvinsi = _dataPerProvinsi

    private var _dataVaksinasi = MutableLiveData<VaksinasiResponse>()
    var dataVaksinasi = _dataVaksinasi

    private var _errorThrowable = MutableLiveData<String>()
    var errorText = _errorThrowable

    init {
        getUpdateDataPandemiPemerintah()
        getDataPerProvinsiPemerintah()
        getDataVaksinasi()
    }

    private fun getDataVaksinasi() {
        addsubscription(serviceApiVaksinasi.getDataVaksinasi().uiSubscribe(
            { _dataVaksinasi.postValue(it) },
            { _errorThrowable.postValue(it.message)},
            {}
        ))
    }

    private fun getDataPerProvinsiPemerintah() {
        addsubscription(servicePemerintah.getDataPerProvince().uiSubscribe(
            {_dataPerProvinsi.postValue(it)},
            {_errorThrowable.postValue(it.message)},
            {}
        ))
    }

    private fun getUpdateDataPandemiPemerintah(){
        addsubscription(
            servicePemerintah.getDataUpdate().uiSubscribe(
                { _dataPandemiUpdatePemerintah.postValue(it) },
                { _errorThrowable.postValue(it.message) },
                {}
            )
        )
    }
}