package com.fadlurahmanf.datapandemi.data

import com.fadlurahmanf.datapandemi.data.response.province.DataPandemiPerProvinsiResponse
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

class ItemPersebaran(
    var id:String?="",
    var province:String?="",
    var dataPerProvinsi: DataPandemiPerProvinsiResponse.DataPerProvinsi,
    var latlng: LatLng?
    ):ClusterItem{
    override fun getPosition(): LatLng {
        return latlng?:LatLng(0.0, 0.0)
    }

    override fun getTitle(): String? {
        return province
    }

    override fun getSnippet(): String? {
        return "Klik untuk detail"
    }
}

class ItemPersebaranNegara(
    var countryName:String?="",
    var latlng: LatLng?,
    var case_confirmed:Int,
    var case_death:Int
):ClusterItem{
    override fun getPosition(): LatLng {
        return latlng?: LatLng(0.0,0.0)
    }

    override fun getTitle(): String? {
        return countryName
    }

    override fun getSnippet(): String? {
        return "Klik untuk detail"
    }

}