package com.fadlurahmanf.datapandemi.di.component

import android.app.Application
import com.fadlurahmanf.datapandemi.di.BaseApp
import com.fadlurahmanf.datapandemi.di.builder.ActivityBuilder
import com.fadlurahmanf.datapandemi.di.module.ApplicationModule
import com.fadlurahmanf.datapandemi.di.module.ViewModelFactoryModule
import com.fadlurahmanf.datapandemi.di.builder.ViewModelBuilder
import com.fadlurahmanf.datapandemi.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class, ActivityBuilder::class,
    ApplicationModule::class, NetworkModule::class,
    ViewModelFactoryModule::class, ViewModelBuilder::class])
interface ApplicationComponent {
    fun inject(app:BaseApp)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application:Application):Builder
        fun buildComponent():ApplicationComponent
    }
}