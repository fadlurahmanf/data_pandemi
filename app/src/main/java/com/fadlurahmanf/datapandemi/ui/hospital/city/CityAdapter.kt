package com.fadlurahmanf.datapandemi.ui.hospital.city

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.data.response.city.CityHospitalResponse

class CityAdapter:RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    private lateinit var onItemCityClickCallback: OnItemCityClickCallback
    private var listCity :ArrayList<CityHospitalResponse.City> = arrayListOf()

    fun setOnItemCityClickCallback(onItemCityClickCallback: OnItemCityClickCallback){
        if (onItemCityClickCallback!=null) this.onItemCityClickCallback = onItemCityClickCallback
    }

    fun setListCity(list:List<CityHospitalResponse.City>){
        if (list!=null){
            listCity.clear()
            listCity.addAll(list)
        }
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var itemName:TextView = view?.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_province_or_city, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var city = listCity[position]
        holder.itemName.text = city.name

        holder.itemView.setOnClickListener {
            onItemCityClickCallback.onItemClicked(city)
        }
    }

    override fun getItemCount(): Int {
        return listCity.size
    }
}