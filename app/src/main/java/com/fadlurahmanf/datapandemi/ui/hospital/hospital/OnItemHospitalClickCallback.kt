package com.fadlurahmanf.datapandemi.ui.hospital.hospital

import com.fadlurahmanf.datapandemi.data.response.hospital.HospitalResponse

interface OnItemHospitalClickCallback {
    fun onItemClicked(hospital:HospitalResponse.Hospital)
}