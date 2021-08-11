package com.fadlurahmanf.datapandemi.di.builder

import com.fadlurahmanf.datapandemi.ui.MainActivity
import com.fadlurahmanf.datapandemi.ui.world.WorldActivity
import com.fadlurahmanf.datapandemi.ui.hospital.city.SearchCityHospitalActivity
import com.fadlurahmanf.datapandemi.ui.hospital.hospital.SearchHospitalActivity
import com.fadlurahmanf.datapandemi.ui.hospital.province.SearchProvinceHospitalActivity
import com.fadlurahmanf.datapandemi.ui.provinsi.ProvinsiActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindWorldActivity(): WorldActivity
    @ContributesAndroidInjector
    abstract fun bindSearchHospitalActivity():SearchHospitalActivity
    @ContributesAndroidInjector
    abstract fun bindSearchCityHospitalActivity():SearchCityHospitalActivity
    @ContributesAndroidInjector
    abstract fun bindSearchProvinceHospitalActivity(): SearchProvinceHospitalActivity
    @ContributesAndroidInjector
    abstract fun bindProvinsiActivity():ProvinsiActivity
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}