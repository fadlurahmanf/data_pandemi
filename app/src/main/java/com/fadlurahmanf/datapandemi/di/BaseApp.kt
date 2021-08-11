package com.fadlurahmanf.datapandemi.di

import android.app.Application
import com.fadlurahmanf.datapandemi.di.component.ApplicationComponent
import com.fadlurahmanf.datapandemi.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class BaseApp:Application(), HasAndroidInjector {

    private lateinit var applicationComponent:ApplicationComponent

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().application(this).buildComponent()
        applicationComponent.inject(this)
    }
}