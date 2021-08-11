package com.fadlurahmanf.datapandemi.base

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson


abstract class AbstractPreference(context: Context){
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var gson: Gson

    abstract fun getParamsGroup():String

    init {
        sharedPreferences = context.getSharedPreferences(getParamsGroup(), Context.MODE_PRIVATE)
        gson = Gson()
    }

    fun initEditSharedPrefenrences(): SharedPreferences.Editor? {
        return sharedPreferences.edit()
    }

    protected fun editContent(tag: String, value:String){
        initEditSharedPrefenrences()?.putString(tag, value)?.commit()
    }

    protected fun getContent(tag: String, defaultValue:String): String? {
        return sharedPreferences.getString(tag, "")
    }

    protected fun getContent(tag: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(tag, 0)
    }

    protected fun <T> editContent(tag:String, obj:T){
        val json = gson.toJson(obj)
        initEditSharedPrefenrences()?.putString(tag, json)?.commit()
    }

    protected fun <T> getContent(tag: String, classOfT:Class<T>): T {
        var json = sharedPreferences.getString(tag, "")
        return gson.fromJson(json, classOfT)
    }

//    protected fun editString(){
//        initEditSharedPrefenrences()?.putString(ParamsValue.COBA_STRING, "VALUE")?.commit()
//    }
//
//    protected fun getString(): String? {
//        return sharedPreferences.getString(ParamsValue.COBA_STRING, "DEFAULT VALUE")
//    }
}