package com.example.capstone_healthycare.listadapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.capstone_healthycare.R
import com.example.capstone_healthycare.databinding.ItemRowRecommendationBinding
import com.example.capstone_healthycare.model.DataRecipes
import com.example.capstone_healthycare.ui.food.FoodDetailActivity

class ListRecommendationAdapter : RecyclerView.Adapter<ListRecommendationAdapter.ListViewHolder>() {
    private val dataRec = ArrayList<DataRecipes>()

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemRowRecommendationBinding.bind(itemView)
    }

    fun setData(items: ArrayList<DataRecipes>) {
        dataRec.clear()
        dataRec.addAll(items)
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListRecommendationAdapter.ListViewHolder {
        val view: View =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_row_recommendation, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListRecommendationAdapter.ListViewHolder, position: Int) {
        val data = dataRec[position]

        with(holder) {
            binding.tvMeal.text = data.strMeal
            binding.tvArea.text = data.strArea
            binding.tvCategory.text = data.strCategory
            Glide.with(itemView.context)
                .load(data.strMealThumb)
                .placeholder(R.drawable.placeholder)
                .into(binding.ivMeal)

            itemView.setOnClickListener {

                val context: Context = it!!.context
                val intent = Intent(context, FoodDetailActivity::class.java)
                intent.putExtra(FoodDetailActivity.EXTRA_RECIPE, data)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)

            }
        }
    }

    override fun getItemCount(): Int {
        return dataRec.size
    }
}