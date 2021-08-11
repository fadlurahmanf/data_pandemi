package com.fadlurahmanf.datapandemi.ui.world

import android.app.Activity
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.ViewModelFactory
import com.fadlurahmanf.datapandemi.base.BaseMvvmActivity
import com.fadlurahmanf.datapandemi.data.ItemPersebaran
import com.fadlurahmanf.datapandemi.data.ItemPersebaranNegara
import com.fadlurahmanf.datapandemi.data.response.country.DataNegaraResponse
import com.fadlurahmanf.datapandemi.databinding.ActivityWorldBinding
import com.fadlurahmanf.datapandemi.ui.dialog.CountryDialog
import com.fadlurahmanf.datapandemi.ui.provinsi.ProvinsiActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.maps.android.clustering.ClusterManager
import dagger.android.AndroidInjection
import org.jetbrains.anko.startActivity
import javax.inject.Inject

class WorldActivity : BaseMvvmActivity() {
    private lateinit var binding:ActivityWorldBinding
    private lateinit var supportMapFragment: SupportMapFragment
    private lateinit var googleMap: GoogleMap

    private lateinit var viewModel: WorldViewModel

    private var listItemPersebaranNegara: ArrayList<ItemPersebaranNegara> = arrayListOf()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    companion object{
        fun newInstance(activity:Activity){
            activity.startActivity<WorldActivity>()
        }
    }

    override fun initMVVM() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(WorldViewModel::class.java)
    }

    override fun initSetup() {
        supportActionBar?.title = "WORLD WIDE"
        initMapFragment()
        initData()
    }

    private fun initData() {
        viewModel.listDataAllCountry.observe(this, Observer {
            listItemPersebaranNegara = getAllItemPersebaranNegara(it)
            setupClusterManager(googleMap)
        })
    }

    private fun getAllItemPersebaranNegara(data: List<DataNegaraResponse>?): java.util.ArrayList<ItemPersebaranNegara> {
        var list:ArrayList<ItemPersebaranNegara> = arrayListOf()

        data?.forEach {
            list.add(ItemPersebaranNegara(
                it.attributes.country,
                LatLng(it.attributes.lat.toDouble(), it.attributes.long.toDouble()),
                it.attributes.total_case_confirmed,
                it.attributes.total_case_deaths
            ))
        }

        return list
    }

    private fun initMapFragment() {
        supportMapFragment = supportFragmentManager.findFragmentById(R.id.mapworld_fragment) as SupportMapFragment
        supportMapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_night))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng( -8.838333, 13.234444), 4f))
            setupClusterManager(googleMap)
        })
    }

    private fun setupClusterManager(googleMap: GoogleMap) {
        if (googleMap!=null){
            val clusterManager = ClusterManager<ItemPersebaranNegara>(this, googleMap)
            googleMap?.setOnCameraIdleListener(clusterManager)
            clusterManager.addItems(listItemPersebaranNegara)
            clusterManager.cluster()

            clusterManager.setOnClusterItemInfoWindowClickListener(object : ClusterManager.OnClusterItemInfoWindowClickListener<ItemPersebaranNegara>{
                override fun onClusterItemInfoWindowClick(item: ItemPersebaranNegara?) {
//                    val bottomSheetDialog = BottomSheetDialog(this@WorldActivity, R.style.Theme_Design_BottomSheetDialog)
//                    val bottomSheetView = LayoutInflater.from(this@WorldActivity).inflate(R.layout.country_bottom_sheet, findViewById<LinearLayout>(R.id.bottomSheet))
//                    bottomSheetDialog.setContentView(bottomSheetView)
//                    bottomSheetDialog.show()
                    println("MASUK CLUSTER CLICK")
                    CountryDialog.newInstance(item?.countryName?:"-", item?.case_confirmed?:0, item?.case_death?:0).show(supportFragmentManager, CountryDialog::class.java.canonicalName)
                }
            })
        }
    }

    override fun initLayout() {
        binding = ActivityWorldBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }
}