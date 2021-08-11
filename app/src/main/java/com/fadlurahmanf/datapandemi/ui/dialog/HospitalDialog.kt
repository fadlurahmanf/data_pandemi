package com.fadlurahmanf.datapandemi.ui.dialog

import android.view.View
import com.fadlurahmanf.datapandemi.base.BaseDialog
import com.fadlurahmanf.datapandemi.databinding.HospitalDialogBinding

class HospitalDialog:BaseDialog() {
    private var phoneClickListener : () -> Unit = {}
    private var mapsClickistener : () -> Unit = {}
    private lateinit var binding:HospitalDialogBinding
    override fun initSetup() {
        binding.ivPhone.setOnClickListener {
            phoneClickListener()
        }
        binding.ivMaps.setOnClickListener {
            mapsClickistener()
        }
    }

    override fun getLayout(): View {
        binding = HospitalDialogBinding.inflate(layoutInflater)
        return binding.root
    }

    fun setupPhoneClickListener(listener:()->Unit){
        this.phoneClickListener = listener
    }

    fun setupMapsClickListener(listener: () -> Unit){
        this.mapsClickistener = listener
    }
}