package com.example.capstone_healthycare.listadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_healthycare.model.RecordsBMI
import com.example.capstone_healthycare.databinding.BmiRowsBinding


class ListAdapterBMI: RecyclerView.Adapter<ListAdapterBMI.MyViewHolder>() {

    private var list = emptyList<RecordsBMI>()

    class MyViewHolder(var binding: BmiRowsBinding): RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(BmiRowsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = list[position]
        holder.binding.tvId.text = currentItem.id.toString()
        holder.binding.tvResult.text = currentItem.BMI
        holder.binding.tvIndicator.text = currentItem.Indicator
    }


    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(bmi: List<RecordsBMI>){
        this.list = bmi
        notifyDataSetChanged()
    }
}