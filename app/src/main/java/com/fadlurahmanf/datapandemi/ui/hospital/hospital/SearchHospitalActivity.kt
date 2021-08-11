package com.fadlurahmanf.datapandemi.ui.hospital.hospital

import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.ViewModelFactory
import com.fadlurahmanf.datapandemi.base.BaseDialog
import com.fadlurahmanf.datapandemi.base.BaseMvvmActivity
import com.fadlurahmanf.datapandemi.data.repo.IntentRepository
import com.fadlurahmanf.datapandemi.data.response.hospital.DetailLocationHospitalResponse
import com.fadlurahmanf.datapandemi.data.response.hospital.HospitalResponse
import com.fadlurahmanf.datapandemi.databinding.ActivitySearchHospitalBinding
import com.fadlurahmanf.datapandemi.extenson.AnimationSet
import com.fadlurahmanf.datapandemi.extenson.observeOnce
import com.fadlurahmanf.datapandemi.ui.dialog.HospitalDialog
import com.fadlurahmanf.datapandemi.ui.dialog.LoadingDialog
import dagger.android.AndroidInjection
import org.jetbrains.anko.startActivity
import java.util.jar.Manifest
import javax.inject.Inject

class SearchHospitalActivity : BaseMvvmActivity(),OnItemHospitalClickCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var intentRepo:IntentRepository

    private lateinit var viewModel:SearchHospitalViewModel

    private lateinit var binding: ActivitySearchHospitalBinding

    private var listHospital:ArrayList<HospitalResponse.Hospital> = arrayListOf()

    companion object{
        fun newInstance(activity:Activity){
            activity.startActivity<SearchHospitalActivity>()
        }
    }

    override fun initMVVM() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchHospitalViewModel::class.java)
    }

    override fun initSetup() {
        overrideAnimation = AnimationSet(R.anim.fade_in, R.anim.stay_in_place)
        supportActionBar?.hide()
        binding.titleCariRumahSakit.text = intentRepo?.searchHospitalIntentData?.cityName?:"Kota"
        initRv()
        initData()
        initAction()
    }

    private fun initAction() {
        binding.etInputSearchHospital.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var listHospitalFilter: ArrayList<HospitalResponse.Hospital> = arrayListOf()
                listHospitalFilter = listHospital?.filter { it-> it.hospitalName.toLowerCase().contains(s.toString().toLowerCase())
                        || it.address.toLowerCase().contains(s.toString().toLowerCase()) } as ArrayList<HospitalResponse.Hospital>
                initRv(listHospitalFilter)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initRv(list:ArrayList<HospitalResponse.Hospital>?= arrayListOf()) {
        binding.rvHospital.layoutManager = LinearLayoutManager(this)
        var adapter = HospitalAdapter()
        adapter.setListHospital(list?: arrayListOf())
        adapter.notifyDataSetChanged()
        adapter.setOnItemHospitalClickCallback(this)
        binding.rvHospital.adapter = adapter
    }

    private fun initData() {
        var dummy:HospitalResponse?=null
        viewModel.listHospital.observe(this, Observer {
            listHospital = it.listHospital?: arrayListOf()
            initRv(listHospital)
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) binding.lottieLoading.visibility = View.VISIBLE
            else binding.lottieLoading.visibility = View.GONE
        })

        binding.titleCariRumahSakit.setOnClickListener {
            println(dummy?.listHospital?.size)
        }
    }

    override fun initLayout() {
        binding = ActivitySearchHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onItemClicked(hospital: HospitalResponse.Hospital) {
        if (hospital!=null){
            var _data = MutableLiveData<DetailLocationHospitalResponse.Data>()
            var data:LiveData<DetailLocationHospitalResponse.Data> = _data
            showLoading()
            viewModel.getHospitalDetailLocation("${hospital.id}").observe(this, Observer {
                _data.postValue(it)
            })
            data.observeOnce(this, Observer<DetailLocationHospitalResponse.Data>{ dataHospital->
                if (dataHospital!=null){
                    dismissLoading()
                    var dialog = HospitalDialog()
                    dialog.apply {
                        setupPhoneClickListener {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "${hospital.phone}"))
                            startActivity(intent)
                        }
                        setupMapsClickListener {
//                            val gmmIntentUri = Uri.parse("geo:${dataHospital.lat},${dataHospital.lng}") // intent for google view
                            val gmmIntentUri = Uri.parse("google.navigation:q=${dataHospital.name}") //intent for google maps navigation
                            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                            mapIntent.setPackage("com.google.android.apps.maps")
                            startActivity(mapIntent)
                        }
                    }
                    dialog.show(supportFragmentManager, BaseDialog::class.java.canonicalName)
                }
            })
        }
    }

}