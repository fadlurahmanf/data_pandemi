package com.fadlurahmanf.datapandemi.data.repo

import android.content.Context
import com.fadlurahmanf.datapandemi.base.AbstractPreference
import com.fadlurahmanf.datapandemi.data.DataProvinsi
import com.fadlurahmanf.datapandemi.data.SearchHospitalIntentData
import com.fadlurahmanf.datapandemi.params.Params
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IntentRepository @Inject constructor(var context: Context):AbstractPreference(context) {
    override fun getParamsGroup(): String {
        return Params.ITEM_INTENT_SEARCH_HOSPITAL
    }

    //data buat intent cluster province
    var dataProvinsi: DataProvinsi?=null
        get() {
            field=getContent(Params.ITEM_PROVINSI, DataProvinsi::class.java)
            return field
        }set(value) {
        field=value
        editContent(Params.ITEM_PROVINSI, value)
    }

    //data buat intent search hospital
    var searchHospitalIntentData: SearchHospitalIntentData?=null
        get() {
            field = getContent(Params.ITEM_INTENT_SEARCH_HOSPITAL, SearchHospitalIntentData::class.java)
            return field
        }set(value) {
        field=value
        editContent(Params.ITEM_INTENT_SEARCH_HOSPITAL, value)
    }

    fun persist(){
        searchHospitalIntentData = searchHospitalIntentData
    }
}