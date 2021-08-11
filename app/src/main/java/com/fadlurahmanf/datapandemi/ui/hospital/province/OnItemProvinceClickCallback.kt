package com.fadlurahmanf.datapandemi.ui.hospital.province

import com.fadlurahmanf.datapandemi.data.response.province.GetProvinceIdResponse

interface OnItemProvinceClickCallback {
    fun onItemClicked(data: GetProvinceIdResponse.Province)
}