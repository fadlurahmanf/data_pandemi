package com.fadlurahmanf.datapandemi.data.repo

import android.content.Context
import com.fadlurahmanf.datapandemi.base.AbstractPreference
import com.fadlurahmanf.datapandemi.data.DataProvinsi
import com.fadlurahmanf.datapandemi.params.Params
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProvinceRepository@Inject constructor(context: Context): AbstractPreference(context)  {
    override fun getParamsGroup(): String {
        return Params.GROUP_ITEM_PROVINSI
    }
}