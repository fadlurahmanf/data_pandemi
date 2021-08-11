package com.fadlurahmanf.datapandemi.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel:ViewModel() {
    fun addsubscription(disposable: Disposable) = CompositeDisposable().add(disposable)
}