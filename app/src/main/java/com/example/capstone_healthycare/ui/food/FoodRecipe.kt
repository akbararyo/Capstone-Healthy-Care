package com.example.capstone_healthycare.ui.food

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstone_healthycare.R
import com.example.capstone_healthycare.databinding.ActivityFoodRecipeBinding
import com.example.capstone_healthycare.listadapter.GridCategoriesAdapter
import com.example.capstone_healthycare.listadapter.ListRecommendationAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class FoodRecipe : AppCompatActivity() {

    private lateinit var binding: ActivityFoodRecipeBinding
    private lateinit var listRecommendationAdapter: ListRecommendationAdapter
    private lateinit var gridCategoriesAdapter: GridCategoriesAdapter
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        initView()
        initListener()
        initViewModel()
    }

    private fun initView() {
        val myColor: Int = Color.parseColor("#1B8045")
        binding.swLayout.setColorSchemeColors(myColor)

        binding.rvRecommendation.setHasFixedSize(true)
        binding.rvCategories.setHasFixedSize(true)

        listRecommendationAdapter = ListRecommendationAdapter()
        listRecommendationAdapter.notifyDataSetChanged()

        binding.rvRecommendation.layoutManager = LinearLayoutManager(this)
        binding.rvRecommendation.adapter = listRecommendationAdapter


        gridCategoriesAdapter = GridCategoriesAdapter()
        gridCategoriesAdapter.notifyDataSetChanged()

        binding.rvCategories.layoutManager =
            GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        binding.rvCategories.adapter = gridCategoriesAdapter
    }

    private fun initListener() {
        binding.swLayout.post {
            showLoading(true)
            homeViewModel.setRecommendation()
            homeViewModel.setCategories()
        }

        binding.swLayout.setOnRefreshListener {
            showLoading(true)
            homeViewModel.setRecommendation()
            homeViewModel.setCategories()
        }
    }

    private fun initViewModel() {
        homeViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(HomeViewModel::class.java)

        homeViewModel.getRecommendation().observe(this, Observer { recommendItems ->
            if (recommendItems.isNotEmpty()) {
                listRecommendationAdapter.setData(recommendItems)
                showLoading(false)
            } else {
                showLoading(false)
            }
        })

        homeViewModel.getCategories().observe(this, Observer { categories ->
            if (categories.isNotEmpty()) {
                gridCategoriesAdapter.setData(categories)
                showLoading(false)
            } else {
                showLoading(false)
            }
        })
    }

    private fun showLoading(loading: Boolean) {
        binding.swLayout.isRefreshing = loading

        if (loading) {

            binding.shimmerRecommendation.startShimmer()
            binding.shimmerCategories.startShimmer()

        } else {

            binding.shimmerRecommendation.stopShimmer()
            binding.shimmerCategories.stopShimmer()
            binding.shimmerRecommendation.visibility = View.GONE
            binding.shimmerCategories.visibility = View.GONE

        }

    }

}