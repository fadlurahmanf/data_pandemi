package com.fadlurahmanf.datapandemi.ui.hospital.hospital

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadlurahmanf.datapandemi.R
import com.fadlurahmanf.datapandemi.data.response.hospital.HospitalResponse

class HospitalAdapter:RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {
    private lateinit var onItemHospitalClickCallback: OnItemHospitalClickCallback
    private var listHospital:ArrayList<HospitalResponse.Hospital> = arrayListOf()

    fun setOnItemHospitalClickCallback(onItemHospitalClickCallback: OnItemHospitalClickCallback){
        this.onItemHospitalClickCallback = onItemHospitalClickCallback
    }

    fun setListHospital(list: List<HospitalResponse.Hospital>){
        if (list!=null){
            listHospital.clear()
            listHospital.addAll(list)
        }
    }
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view) {
        var hospitalName:TextView = view?.findViewById(R.id.tv_hospitalname)
        var address:TextView = view?.findViewById(R.id.tv_addressHospital)
        var bed_availability:TextView = view.findViewById(R.id.tv_bed_availability)
        var jumlah_antrian:TextView = view.findViewById(R.id.tv_jumlah_antrian)
        var info:TextView = view?.findViewById(R.id.tv_info_last_update)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_hospital, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var hospital = listHospital[position]

        holder.hospitalName.text = hospital.hospitalName
        holder.bed_availability.text = hospital.bed_availability.toString()
        holder.jumlah_antrian.text = hospital.queue.toString()
        holder.address.text = hospital.address
        holder.info.text = hospital.info

        holder.itemView.setOnClickListener {
            onItemHospitalClickCallback.onItemClicked(hospital)
        }
    }

    override fun getItemCount(): Int {
        return listHospital.size
    }
}