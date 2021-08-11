package com.fadlurahmanf.datapandemi.ui.world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.fadlurahmanf.datapandemi.api.ServiceKawalCorona
import com.fadlurahmanf.datapandemi.base.BaseViewModel
import com.fadlurahmanf.datapandemi.data.response.country.DataNegaraResponse
import com.fadlurahmanf.datapandemi.extenson.uiSubscribe
import javax.inject.Inject

class WorldViewModel @Inject constructor(
    var serviceKawalCorona: ServiceKawalCorona
):BaseViewModel() {
    private var _listDataAllCountry = MutableLiveData<List<DataNegaraResponse>>()
    var listDataAllCountry:LiveData<List<DataNegaraResponse>> = _listDataAllCountry

    private var _errorText = MutableLiveData<String>()
    var errorText:LiveData<String> = _errorText

    init {
        getDataAllCountry()
    }

    private fun getDataAllCountry() {
        addsubscription(serviceKawalCorona.getListDataAllCountry().uiSubscribe(
            { _listDataAllCountry.postValue(it) },
            { _errorText.postValue(it.message) },
            {}
        ))
    }
}