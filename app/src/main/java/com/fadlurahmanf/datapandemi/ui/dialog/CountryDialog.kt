package com.fadlurahmanf.datapandemi.ui.dialog

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.base.BaseBottomSheet
import com.fadlurahmanf.datapandemi.base.BaseDialog
import com.fadlurahmanf.datapandemi.databinding.CountryBottomSheetBinding
import com.fadlurahmanf.datapandemi.extenson.tigaAngkaBelakangKoma
import com.fadlurahmanf.datapandemi.params.Params
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CountryDialog():BaseBottomSheet() {
    private lateinit var binding: CountryBottomSheetBinding

    companion object{
        fun newInstance(countryName:String, caseConfirmed:Int, caseDeath:Int):CountryDialog{
            val fragment = CountryDialog()
            val bundle = Bundle()
            bundle.putString(Params.INTENT_COUNTRY_NAME, countryName)
            bundle.putInt(Params.INTENT_CASE_CONFIRMED, caseConfirmed)
            bundle.putInt(Params.INTENT_CASE_DEATH, caseDeath)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var countryName = "-"
    private var caseConfirmed = 0
    private var caseDeath = 0

    override fun initSetup() {
        initBundle()
        initData()
    }

    private fun initData() {
        binding.tvCountryName.text = countryName
        binding.tvTotalCases.text = caseConfirmed.toString().tigaAngkaBelakangKoma()
        binding.tvTotalDead.text = caseDeath.toString().tigaAngkaBelakangKoma()
    }

    private fun initBundle() {
        arguments?.let {
            countryName = it.getString(Params.INTENT_COUNTRY_NAME, "")
            caseConfirmed = it.getInt(Params.INTENT_CASE_CONFIRMED, 0)
            caseDeath = it.getInt(Params.INTENT_CASE_DEATH,0)
        }
    }

    override fun getLayout(): View {
        binding = CountryBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }
}