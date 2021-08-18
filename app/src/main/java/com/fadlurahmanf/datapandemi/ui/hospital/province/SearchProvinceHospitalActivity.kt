package com.fadlurahmanf.datapandemi.ui.hospital.province

import android.app.Activity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.ViewModelFactory
import com.fadlurahmanf.datapandemi.base.BaseMvvmActivity
import com.fadlurahmanf.datapandemi.data.SearchHospitalIntentData
import com.fadlurahmanf.datapandemi.data.repo.IntentRepository
import com.fadlurahmanf.datapandemi.data.repo.ProvinceRepository
import com.fadlurahmanf.datapandemi.data.response.province.GetProvinceIdResponse
import com.fadlurahmanf.datapandemi.databinding.ActivitySearchProvinceHospitalBinding
import com.fadlurahmanf.datapandemi.extenson.AnimationSet
import com.fadlurahmanf.datapandemi.extenson.DataDummy
import com.fadlurahmanf.datapandemi.ui.hospital.city.SearchCityHospitalActivity
import dagger.android.AndroidInjection
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SearchProvinceHospitalActivity : BaseMvvmActivity(), OnItemProvinceClickCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var intentRepo:IntentRepository

    private lateinit var viewModelProvince: SearchProvinceHospitalViewModel
    private lateinit var binding: ActivitySearchProvinceHospitalBinding

    private var listProvince:ArrayList<GetProvinceIdResponse.Province> = arrayListOf()

    companion object{
        fun newInstance(activity:Activity){
            activity.startActivity<SearchProvinceHospitalActivity>()
        }
    }
    override fun initMVVM() {
        viewModelProvince = ViewModelProvider(this, viewModelFactory).get(
            SearchProvinceHospitalViewModel::class.java)
    }

    override fun initSetup() {
        overrideAnimation = AnimationSet(R.anim.fade_in, R.anim.stay_in_place)
        supportActionBar?.hide()
        initRv()
        initData()
        initAction()
    }

    private fun initAction() {
        binding.etInputSearchProvince.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var listProvinceFilter:ArrayList<GetProvinceIdResponse.Province> = arrayListOf()
                listProvinceFilter = listProvince.filter { it.nameProvince.toLowerCase().contains(s.toString().toLowerCase()) } as ArrayList<GetProvinceIdResponse.Province>
                initRv(listProvinceFilter)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun initLayout() {
        binding = ActivitySearchProvinceHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initRv(listProvince:ArrayList<GetProvinceIdResponse.Province>?= arrayListOf()) {
        binding.rvProvince.layoutManager = LinearLayoutManager(this)
        var adapter = ProvinceAdapter()
        adapter.setListProvince(listProvince?: arrayListOf())
        adapter.onItemProvinceCallback(this)
        binding.rvProvince.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initData() {
        viewModelProvince.listAllProvince.observe(this, Observer {
            listProvince = it.listProvince?: arrayListOf()
            initRv(listProvince)
        })
        
        viewModelProvince.errorThrowable.observe(this, Observer {
            // TODO: 05/08/2021 Later 
            println("GAGAL $it")
        })

        viewModelProvince.isLoading.observe(this, Observer {
            if (it) binding.lottieLoading.visibility = View.VISIBLE
            else binding.lottieLoading.visibility = View.GONE
        })

        binding.titleCariRumahSakit.setOnClickListener {
            binding.lottieLoading.visibility = View.VISIBLE
        }
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun onItemClicked(data: GetProvinceIdResponse.Province) {
        if (data!=null){
            saveToRepo(data)
        }
    }

    private fun saveToRepo(data: GetProvinceIdResponse.Province) {
        intentRepo.searchHospitalIntentData = SearchHospitalIntentData(
            idProvince = data?.id?:"",
            nameProvince = data?.nameProvince?:""
        )
        intentRepo.dataDummy = DataDummy()
        intentRepo.dataDummy?.data1 = "INI DATA KE 1"
        println("MASUK SEARCH PROVINCE ${intentRepo.dataDummy?.toString()}")
        SearchCityHospitalActivity.newInstance(this)
    }

}