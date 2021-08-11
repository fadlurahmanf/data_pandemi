package com.fadlurahmanf.datapandemi.di.builder

import androidx.lifecycle.ViewModel
import com.fadlurahmanf.datapandemi.ui.MainViewModel
import com.fadlurahmanf.datapandemi.ui.hospital.city.SearchCityHospitalViewModel
import com.fadlurahmanf.datapandemi.ui.hospital.hospital.SearchHospitalViewModel
import com.fadlurahmanf.datapandemi.ui.hospital.province.SearchProvinceHospitalViewModel
import com.fadlurahmanf.datapandemi.ui.world.WorldViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBuilder {
    @Binds
    @IntoMap
    @ViewModelKey(WorldViewModel::class)
    abstract fun bindWorldViewModel(worldViewModel: WorldViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchHospitalViewModel::class)
    abstract fun bindSearchHospitalViewModel(searchHospitalViewModel: SearchHospitalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchProvinceHospitalViewModel::class)
    abstract fun bindSearchProvinceHospitalViewModel(searchProvinceHospitalViewModel: SearchProvinceHospitalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchCityHospitalViewModel::class)
    abstract fun bindSearchCityHospitalViewModel(searchCityHospitalViewModel: SearchCityHospitalViewModel): ViewModel
}