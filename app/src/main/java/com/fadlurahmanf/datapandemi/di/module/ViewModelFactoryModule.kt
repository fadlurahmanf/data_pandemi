package com.fadlurahmanf.datapandemi.di.module

import androidx.lifecycle.ViewModelProvider
import com.fadlurahmanf.datapandemi.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}