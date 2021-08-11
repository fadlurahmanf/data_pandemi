package com.fadlurahmanf.datapandemi.base

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.fadlurahmanf.datapandemi.extenson.AnimationSet
import com.fadlurahmanf.datapandemi.ui.dialog.LoadingDialog
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity():AppCompatActivity() {
    var loadingDialog:LoadingDialog?=null

    protected var overrideAnimation: AnimationSet? = null
        set(value) {
            if(null != value) {
                overridePendingTransition(value.animEnterIn, value.animEnterOut)
                field = value
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
        initLayout()
        internalSetup()
        initSetup()
    }

    open fun internalSetup(){}

    abstract fun initSetup()

    abstract fun initLayout()

    abstract fun inject()

    fun addsubscription(disposable:Disposable) = CompositeDisposable().add(disposable)

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkPermission(activity:Activity, manifestPermission:String){
        if (ContextCompat.checkSelfPermission(activity, manifestPermission)!=PackageManager.PERMISSION_GRANTED) requestPermissions(arrayOf(manifestPermission), 1)
    }

    fun showLoading(){
        loadingDialog = LoadingDialog(this)
        loadingDialog?.startLoading()
    }

    fun dismissLoading(){
        if (loadingDialog!=null) loadingDialog?.dismissDialog()
    }
}