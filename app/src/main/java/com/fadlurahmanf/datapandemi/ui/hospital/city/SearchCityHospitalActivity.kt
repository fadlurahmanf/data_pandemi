package com.fadlurahmanf.datapandemi.ui.hospital.city

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
import com.fadlurahmanf.datapandemi.data.response.city.CityHospitalResponse
import com.fadlurahmanf.datapandemi.databinding.ActivitySearchCityHospitalBinding
import com.fadlurahmanf.datapandemi.extenson.AnimationSet
import com.fadlurahmanf.datapandemi.ui.hospital.hospital.SearchHospitalActivity
import dagger.android.AndroidInjection
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class SearchCityHospitalActivity : BaseMvvmActivity(), OnItemCityClickCallback {
    private lateinit var binding:ActivitySearchCityHospitalBinding

    private lateinit var viewModel: SearchCityHospitalViewModel

    @Inject
    lateinit var intentRepo:IntentRepository

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var listCity:ArrayList<CityHospitalResponse.City> = arrayListOf()

    companion object{
        fun newInstance(activity:Activity){
            activity.startActivity<SearchCityHospitalActivity>()
        }
    }

    override fun initMVVM() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchCityHospitalViewModel::class.java)
    }

    override fun initSetup() {
        overrideAnimation = AnimationSet(R.anim.fade_in, R.anim.stay_in_place)
        supportActionBar?.hide()
        binding.titleCariRumahSakit.text = intentRepo?.searchHospitalIntentData?.nameProvince?:"Provinsi"
        initRv()
        initData()
        initAction()
    }

    private fun initAction() {
        binding.etInputSearchCity.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                var listCityFilter:ArrayList<CityHospitalResponse.City> = arrayListOf()
                listCityFilter = listCity.filter { it-> it.name.toLowerCase().contains(s?.toString()?.toLowerCase()?:"") } as ArrayList<CityHospitalResponse.City>
                initRv(listCityFilter)
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initData() {
        viewModel.listCity.observe(this, Observer {
            listCity = it?.listCity?: arrayListOf()
            initRv(listCity)
        })

        viewModel.isLoading.observe(this, Observer {
            if (it) binding.lottieLoading.visibility = View.VISIBLE
            else binding.lottieLoading.visibility = View.GONE
        })
    }

    private fun initRv(listCity:ArrayList<CityHospitalResponse.City>?= arrayListOf()) {
        binding.rvCity.layoutManager = LinearLayoutManager(this)
        var adapter = CityAdapter()
        adapter.setListCity(listCity?: arrayListOf())
        adapter.setOnItemCityClickCallback(this)
        binding.rvCity.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    override fun initLayout() {
        binding = ActivitySearchCityHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }

    override fun onItemClicked(city: CityHospitalResponse.City) {
        if (city!=null){
            saveToRepo(city)
            SearchHospitalActivity.newInstance(this)
        }
    }

    private fun saveToRepo(city: CityHospitalResponse.City) {
        intentRepo.searchHospitalIntentData = SearchHospitalIntentData(
            idProvince = intentRepo.searchHospitalIntentData?.idProvince?:"",
            nameProvince = intentRepo.searchHospitalIntentData?.nameProvince?:"",
            idCity = city.id?:"",
            cityName = city.name?:""
        )
        intentRepo.dataDummy?.data2 = "INI DATA KE DUA"
        println("MASUK SEARCH CITY ${intentRepo.dataDummy?.toString()}")

        intentRepo.nullDataDummy()
        println(intentRepo.dataDummy?.toString())
    }

}