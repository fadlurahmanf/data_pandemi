package com.fadlurahmanf.datapandemi.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.graphics.drawable.ColorDrawable
import android.view.View
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.base.BaseDialog
import com.fadlurahmanf.datapandemi.databinding.LoadingDialogBinding

class LoadingDialog(activity: Activity) {
    lateinit var dialog:AlertDialog
    var viewLayout:View = View.inflate(activity, R.layout.loading_dialog, null)

    var builder: AlertDialog.Builder = AlertDialog.Builder(activity).setView(viewLayout).setCancelable(false)


    fun startLoading(){
        dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }
}