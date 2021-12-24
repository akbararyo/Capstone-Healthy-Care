package com.example.capstone_healthycare.ui.food

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.capstone_healthycare.R
import com.example.capstone_healthycare.databinding.ActivityFilterRecipesBinding
import com.example.capstone_healthycare.listadapter.ListFilteredRecipesAdapter
import com.example.capstone_healthycare.model.DataCategories

class FilterRecipesActivity : AppCompatActivity(), View.OnClickListener {
    companion object {
        const val EXTRA_CATEGORY = "extra_category"
    }

    private lateinit var binding: ActivityFilterRecipesBinding
    private lateinit var filterCategoriesViewModel: FilterCategoriesViewModel
    private lateinit var listFilteredRecipesAdapter: ListFilteredRecipesAdapter
    private lateinit var strCategory: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFilterRecipesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        initViewModel()
        initListener()
    }

    private fun initView() {
        val selectedCategory: DataCategories? = intent.getParcelableExtra(EXTRA_CATEGORY)

        if (selectedCategory != null) {
            strCategory = selectedCategory.strCategory.toString()
            binding.toolbarMenu.tvTitle.text = selectedCategory.strCategory
        }

        binding.rvRecipes.setHasFixedSize(true)

        listFilteredRecipesAdapter = ListFilteredRecipesAdapter()
        listFilteredRecipesAdapter.notifyDataSetChanged()

        binding.rvRecipes.layoutManager = LinearLayoutManager(this)
        binding.rvRecipes.adapter = listFilteredRecipesAdapter
    }

    private fun initViewModel() {
        filterCategoriesViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FilterCategoriesViewModel::class.java)

        filterCategoriesViewModel.getRecipes().observe(this, Observer { recipes ->
            if (recipes.isNotEmpty()) {
                listFilteredRecipesAdapter.setData(recipes)
            }
        })
    }

    private fun initListener() {
        filterCategoriesViewModel.setRecipes(strCategory)
        binding.toolbarMenu.ibBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.ib_back -> {
                onBackPressed()
            }
        }
    }
}