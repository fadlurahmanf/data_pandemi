package com.fadlurahmanf.datapandemi.ui.hospital.province

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.data.response.province.GetProvinceIdResponse

class ProvinceAdapter:RecyclerView.Adapter<ProvinceAdapter.ViewHolder>() {
    private var listProvince:ArrayList<GetProvinceIdResponse.Province> = arrayListOf()
    private lateinit var onItemClickCallBack: OnItemProvinceClickCallback

    fun onItemProvinceCallback(onItemProvinceClickCallback: OnItemProvinceClickCallback){
        if (onItemProvinceClickCallback!=null) onItemClickCallBack = onItemProvinceClickCallback
    }

    fun setListProvince(list:List<GetProvinceIdResponse.Province>){
        if (list!=null){
            listProvince.addAll(list)
        }
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var itemName:TextView = view.findViewById(R.id.item_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_province_or_city, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var province = listProvince[position]
        holder.itemName.text = province.nameProvince?:""

        holder.itemView.setOnClickListener {
            onItemClickCallBack.onItemClicked(province)
        }
    }

    override fun getItemCount(): Int {
        return listProvince.size
    }
}