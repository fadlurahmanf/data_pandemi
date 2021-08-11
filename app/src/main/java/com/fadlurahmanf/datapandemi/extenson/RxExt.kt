package com.fadlurahmanf.datapandemi.extenson

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun <T : Any> Observable<T>.uiSubscribe(onNext: (T) -> Unit = {},
                                        onError: (Throwable) -> Unit = {},
                                        onComplete: () -> Unit = {}): Disposable {
    return this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(onNext, onError, onComplete)
}