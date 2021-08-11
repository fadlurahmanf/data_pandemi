package com.fadlurahmanf.datapandemi.ui.hospital.city

import com.fadlurahmanf.datapandemi.data.response.city.CityHospitalResponse

interface OnItemCityClickCallback {
    fun onItemClicked(city:CityHospitalResponse.City)
}