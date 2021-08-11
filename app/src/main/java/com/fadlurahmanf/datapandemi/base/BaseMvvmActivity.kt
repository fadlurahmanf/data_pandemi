package com.fadlurahmanf.datapandemi.base

abstract class BaseMvvmActivity:BaseActivity() {

    override fun internalSetup() {
        initMVVM()
        super.internalSetup()
    }

    abstract fun initMVVM()
}