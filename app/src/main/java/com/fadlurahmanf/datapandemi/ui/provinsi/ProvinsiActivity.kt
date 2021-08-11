package com.fadlurahmanf.datapandemi.ui.provinsi

import android.app.Activity
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.base.BaseMvvmActivity
import com.fadlurahmanf.datapandemi.data.DataProvinsi
import com.fadlurahmanf.datapandemi.data.repo.IntentRepository
import com.fadlurahmanf.datapandemi.data.repo.ProvinceRepository
import com.fadlurahmanf.datapandemi.databinding.ActivityProvinsiBinding
import com.fadlurahmanf.datapandemi.extenson.AnimationSet
import com.fadlurahmanf.datapandemi.extenson.tigaAngkaBelakangKoma
import dagger.android.AndroidInjection
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class ProvinsiActivity : BaseMvvmActivity() {
    private lateinit var binding: ActivityProvinsiBinding

    private var dataItem:DataProvinsi?=null
    @Inject
    lateinit var intentRepo:IntentRepository

    companion object{
        fun newInstance(activity: Activity){
            activity.startActivity<ProvinsiActivity>()
        }
    }

    override fun initMVVM() {
//        TODO("Not yet implemented")
    }

    override fun initSetup() {
        overrideAnimation = AnimationSet(R.anim.fade_in, R.anim.stay_in_place)
        supportActionBar?.title = "${intentRepo.dataProvinsi?.namaProvinsi}"
        initView()
    }

    private fun initView() {
        binding.tvTotalCases.text = intentRepo.dataProvinsi?.totalKasus?.jumlahKasus?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvDailyIncrement.text = "+ ${intentRepo.dataProvinsi?.penambahan?.positif?.toString()?.tigaAngkaBelakangKoma()?:"0"}"
        binding.tvTotalCured.text = intentRepo.dataProvinsi?.totalKasus?.jumlahSembuh?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvDailyIncrementCured.text = "+ ${intentRepo.dataProvinsi?.penambahan?.sembuh?.toString()?.tigaAngkaBelakangKoma()?:"0"}"
        binding.tvJumlahKasusPria.text = intentRepo.dataProvinsi?.jenisKelamin?.pria?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasusWanita.text = intentRepo.dataProvinsi?.jenisKelamin?.wanita?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasus05.text = intentRepo.dataProvinsi?.kelompokUmur?.nol_sd_5?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasus618.text = intentRepo.dataProvinsi?.kelompokUmur?.enam_sd_18?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasus1930.text = intentRepo.dataProvinsi?.kelompokUmur?.sembilanBelas_sd_30?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasus3145.text = intentRepo.dataProvinsi?.kelompokUmur?.tigaSatu_sd_45?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasus4659.text = intentRepo.dataProvinsi?.kelompokUmur?.empatEnam_sd_59?.toString()?.tigaAngkaBelakangKoma()?:"0"
        binding.tvJumlahKasusLebihDari60.text = intentRepo.dataProvinsi?.kelompokUmur?.lebih_dari_60?.toString()?.tigaAngkaBelakangKoma()?:"0"
    }

    override fun initLayout() {
       binding = ActivityProvinsiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }
}