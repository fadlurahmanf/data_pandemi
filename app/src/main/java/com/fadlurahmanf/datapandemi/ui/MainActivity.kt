package com.fadlurahmanf.datapandemi.ui


import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Color
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.fadlurahmanf.datapandemi.BuildConfig
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.ViewModelFactory
import com.fadlurahmanf.datapandemi.api.ServicePemerintah
import com.fadlurahmanf.datapandemi.base.BaseMvvmActivity
import com.fadlurahmanf.datapandemi.data.DataProvinsi
import com.fadlurahmanf.datapandemi.data.ItemPersebaran
import com.fadlurahmanf.datapandemi.data.repo.IntentRepository
import com.fadlurahmanf.datapandemi.data.response.UpdateDataPandemiPemerintahRespone
import com.fadlurahmanf.datapandemi.data.response.province.DataPandemiPerProvinsiResponse
import com.fadlurahmanf.datapandemi.databinding.ActivityMainBinding
import com.fadlurahmanf.datapandemi.extenson.*
import com.fadlurahmanf.datapandemi.ui.hospital.province.SearchProvinceHospitalActivity
import com.fadlurahmanf.datapandemi.ui.provinsi.ProvinsiActivity
import com.fadlurahmanf.datapandemi.ui.world.WorldActivity
import com.github.aachartmodel.aainfographics.aachartcreator.*
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.maps.android.clustering.ClusterManager
import dagger.android.AndroidInjection
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList


class MainActivity : BaseMvvmActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private lateinit var supportMapFragment:SupportMapFragment
    private lateinit var googleMap: GoogleMap
    private var listItemPersebaran:ArrayList<ItemPersebaran> = arrayListOf()

    @Inject
    lateinit var intentRepo:IntentRepository

    @Inject
    lateinit var pemerintahService:ServicePemerintah

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun initMVVM() {
         viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun initSetup() {
        supportActionBar?.title = "TAUFIK FADLURAHMAN FAJARI"
        initData()
        initView()
        initMapFragment()
        initAction()
//        setupNewLineChart2()
    }

    private fun initAction() {
        binding.ivWorld.setOnClickListener {
            WorldActivity.newInstance(this)
        }
        binding.drawer.clVaksinasi.setOnClickListener {
            val ai: ApplicationInfo = applicationContext.packageManager
                .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
            val value = ai.metaData["API_KEY_2"]
            Toast.makeText(this, value.toString(), Toast.LENGTH_LONG).show()
        }
    }

    private fun initMapFragment() {
        supportMapFragment = supportFragmentManager.findFragmentById(R.id.mapfragment) as SupportMapFragment
        supportMapFragment.getMapAsync(OnMapReadyCallback {
            googleMap = it
            googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style_night))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(-10.471834, 105.605532), 5f))
            setupClusterManager(googleMap)
        })
    }

    private fun setupClusterManager(googleMap: GoogleMap?) {
       if (googleMap!=null){
           val clusterManager = ClusterManager<ItemPersebaran>(this, googleMap)
           googleMap?.setOnCameraIdleListener(clusterManager)
           clusterManager.addItems(listItemPersebaran)
           clusterManager.cluster()

           clusterManager.setOnClusterItemInfoWindowClickListener(object :ClusterManager.OnClusterItemInfoWindowClickListener<ItemPersebaran>{
               override fun onClusterItemInfoWindowClick(item: ItemPersebaran?) {
                   saveToRepo(item)
                   ProvinsiActivity.newInstance(this@MainActivity)
               }
           })
       }
    }

    private fun saveToRepo(item: ItemPersebaran?) {
        intentRepo.dataProvinsi = DataProvinsi(
            id = item?.id?:"0",
            namaProvinsi = item?.province?:"",
            totalKasus = DataProvinsi.TotalKasus(
                jumlahKasus = item?.dataPerProvinsi?.jumlah_kasus?:0,
                jumlahSembuh = item?.dataPerProvinsi?.jumlah_sembuh?:0,
                jumlahMeninggal = item?.dataPerProvinsi?.jumlah_meninggal?:0,
                jumlahDiRawat = item?.dataPerProvinsi?.jumlah_dirawat?:0
            ),
            penambahan = DataProvinsi.Penambahan(
                positif = item?.dataPerProvinsi?.penambahan?.positif?:0,
                sembuh = item?.dataPerProvinsi?.penambahan?.sembuh?:0,
                meninggal = item?.dataPerProvinsi?.penambahan?.meninggal?:0
            ),
            jenisKelamin = DataProvinsi.JenisKelamin(
                pria = item?.dataPerProvinsi?.jenis_kelamin?.get(0)?.jumlah,
                wanita = item?.dataPerProvinsi?.jenis_kelamin?.get(1)?.jumlah
            ),
            kelompokUmur = DataProvinsi.KelompokUmur(
                nol_sd_5 = item?.dataPerProvinsi?.kelompok_umur?.get(0)?.jumlah,
                enam_sd_18 = item?.dataPerProvinsi?.kelompok_umur?.get(1)?.jumlah,
                sembilanBelas_sd_30 = item?.dataPerProvinsi?.kelompok_umur?.get(2)?.jumlah,
                tigaSatu_sd_45 = item?.dataPerProvinsi?.kelompok_umur?.get(3)?.jumlah,
                empatEnam_sd_59 = item?.dataPerProvinsi?.kelompok_umur?.get(4)?.jumlah,
                lebih_dari_60 = item?.dataPerProvinsi?.kelompok_umur?.get(5)?.jumlah,
            )
        )
    }

    private fun getAllItemPersebaran(data: DataPandemiPerProvinsiResponse): java.util.ArrayList<ItemPersebaran> {
        var list:ArrayList<ItemPersebaran> = arrayListOf()
        var listProvinsi:ArrayList<IdProvinsi> = DummyProvinsi().getDummyAllProvince()

        data.list_data?.forEach { dataResponse->
            listProvinsi.forEach { dataProvinsiDummy->
                if (dataResponse.provinsi.toLowerCase()?.replace(" ", "")==dataProvinsiDummy.name.toLowerCase()?.replace(" ", "")){
                    list.add(ItemPersebaran(dataProvinsiDummy.id,"${dataResponse?.provinsi}", dataResponse, LatLng(dataProvinsiDummy?.lat, dataProvinsiDummy?.lng)))
                }
            }
        }
        return list
    }

    private fun initView() {
        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 500
        }
    }

    private fun initData() {

        viewModel.dataPandemiUpdatePemerintah.observe(this, Observer {
            binding.drawer.tvTotalCases.text = it.dataTotal.jumlah_positif.toString().tigaAngkaBelakangKoma()
            binding.drawer.tvDailyIncrement.text = "+ ${it.updateToday.penambahan.jumlah_positif.toString().tigaAngkaBelakangKoma()}"

            binding.drawer.tvTotalCured.text = it.dataTotal.jumlah_negatif.toString().tigaAngkaBelakangKoma()
            binding.drawer.tvDailyIncrementCured.text = "+ ${it.updateToday.penambahan.jumlah_sembuh.toString().tigaAngkaBelakangKoma()}"
            setupGraphicChart(it)
            setupNewLineChart(it)
//            setupNewLineChart2()
        })

        viewModel.dataPerProvinsi.observe(this, Observer {
            listItemPersebaran = getAllItemPersebaran(it)
            setupClusterManager(googleMap)
        })

        viewModel.dataVaksinasi.observe(this, Observer {
            var splitTanggalKomplit = it.lastUpdate.split("T")
            var splitTanggal = splitTanggalKomplit[0].split("-")
            var hari = splitTanggal.last().replace("0","")
            var bulan = splitTanggal[1].replace("0", "").convertBulanAngkaKeNama()
            var tahun = splitTanggal.first()
            binding.drawer.tvLastUpdateVaksinasi.text = getString(R.string.terakhir_update_vaksin, "$hari $bulan $tahun")
            var percentVaksinasi1 = it.vaksinasi1.toFloat().div(it.totalSasaran.toFloat()).times(100)
            var percentVaksinasi2 = it.vaksinasi2.toFloat().div(it.totalSasaran.toFloat()).times(100)

            binding.drawer.pvVaksinasi1.setProgress(percentVaksinasi1, true)
            binding.drawer.pvVaksinasi2.setProgress(percentVaksinasi2, true)

        })

        binding.drawer.clHospitalSearch.setOnClickListener {
            SearchProvinceHospitalActivity.newInstance(this)
        }

        binding.drawer.clTotalCases.setOnClickListener {
            // TODO: 04/08/2021
        }

        viewModel.errorText.observe(this, Observer {
            Toast.makeText(this, "$it", Toast.LENGTH_LONG).show()
        })
    }


    private fun setupGraphicChart(it: UpdateDataPandemiPemerintahRespone?) {

        var listSembuh = ArrayList<Int>()
        var listPositif = ArrayList<Int>()
        for (i in 0 until it?.updateToday?.listharian?.size!!){
            if (i%5==0){
                listSembuh.add(it?.updateToday?.listharian[i]?.jumlahSembuh?.value)
                listPositif.add(it?.updateToday?.listharian[i]?.jumlahPositif?.value)
            }
        }
        val aaChartModel : AAChartModel = AAChartModel()
            .chartType(AAChartType.Spline)
            .title("Grafik Pandemi Indonesia")
            .backgroundColor("#FF000000")
            .yAxisTitle("Jumlah")
            .xAxisVisible(false)
//            .xAxisLabelsEnabled(true)
            .series(arrayOf(
                AASeriesElement()
                    .name("SEMBUH")
                    .data(listSembuh.toTypedArray()),
                AASeriesElement()
                    .name("POSITIF")
                    .data(listPositif.toTypedArray()),
            )
            )
        binding.drawer.aaChartView.aa_drawChartWithChartModel(aaChartModel)
    }

    private fun setupNewLineChart(it: UpdateDataPandemiPemerintahRespone?){
        var listSembuh = ArrayList<Entry>()
        var listSembuhLabel = ArrayList<String>()
        var x:Int = 0
        for (i in 0 until it?.updateToday?.listharian?.size!!){
            if (i%5==0){
                listSembuh.add(Entry(x.toFloat(), it.updateToday.listharian[i].jumlahSembuh.value.toFloat()))
                listSembuhLabel.add(it.updateToday.listharian[i].keyAsString.split("T")[0])
                x++
            }
        }

        val sembuhDataSet = LineDataSet(listSembuh, "Sembuh")
        sembuhDataSet.setDrawCircles(false)
        sembuhDataSet.valueTextColor = ContextCompat.getColor(this, R.color.white)
        sembuhDataSet.highLightColor = ContextCompat.getColor(this, R.color.teal_200)

        var yAxisL= binding.drawer.lineChart.getAxis(YAxis.AxisDependency.LEFT)
        yAxisL.setDrawGridLines(true)
        yAxisL.enableGridDashedLine(5f, 5f, 5f)
        yAxisL.textColor = ContextCompat.getColor(this, R.color.white)
        yAxisL.axisMaximum = 45000f
        val yAxisR = binding.drawer.lineChart.getAxis(YAxis.AxisDependency.RIGHT)
        yAxisR.setDrawGridLines(false)
        yAxisR.isEnabled = false
        val xAxis = binding.drawer.lineChart.xAxis
        xAxis.textColor = ContextCompat.getColor(this, R.color.white)
        xAxis.setDrawGridLines(false)
        xAxis.labelCount = 2
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.valueFormatter = newAxisFormatter(listSembuhLabel)
        binding.drawer.lineChart.data = LineData(sembuhDataSet)
        binding.drawer.lineChart.setScaleEnabled(false)
        var marker = customMarkerView(this, R.layout.custom_markerview_linechart)
        binding.drawer.lineChart.marker = marker
        binding.drawer.lineChart.invalidate()

    }

    private inner class customMarkerView(context:Context, layoutResource:Int): MarkerView(context, layoutResource) {
        private lateinit var text1:TextView
        init {
            text1 = findViewById(R.id.cobacoba)
        }
        override fun refreshContent(e: Entry?, highlight: Highlight?) {
            val value = e?.y?.toInt()
            text1.text = value.toString()
            super.refreshContent(e, highlight)
        }
        override fun getOffsetForDrawingAtPoint(xpos: Float, ypos: Float): MPPointF {
            return MPPointF(-width / 2f, -height - 10f)
        }
    }

    private fun newAxisFormatter(listDate:ArrayList<String>):IndexAxisValueFormatter{
        val formatter: IndexAxisValueFormatter = object : IndexAxisValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                println("MASUK ${listDate.size}")
                var position = (value).toInt()
                val newFormattedDate = listDate[position].convertCompletedDateToMonthAndYear()
                return newFormattedDate
            }
        }
        return formatter
    }

    override fun initLayout() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun inject() {
        AndroidInjection.inject(this)
    }
}