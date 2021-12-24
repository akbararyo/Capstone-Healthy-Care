package com.example.capstone_healthycare.listadapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_healthycare.databinding.ItemRowRecipesBinding
import com.example.capstone_healthycare.model.DataRecipes

class ListFilteredRecipesAdapter: RecyclerView.Adapter<ListFilteredRecipesAdapter.ListViewHolder>() {
    private val dataFiltered = ArrayList<DataRecipes>()

    fun setData(items: ArrayList<DataRecipes>) {
        dataFiltered.clear()
        dataFiltered.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowRecipesBinding.bind(itemView)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFilteredRecipesAdapter.ListViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: ListFilteredRecipesAdapter.ListViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}