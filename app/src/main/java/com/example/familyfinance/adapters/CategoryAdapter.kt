package com.example.familyfinance.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.familyfinance.R
import com.example.familyfinance.databinding.ListCategoriesBinding
import com.example.familyfinance.models.Category

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryHolder>() {

    val categoryList = ArrayList<Category>()

    class CategoryHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = ListCategoriesBinding.bind(item)
        fun bind(category: Category) = with(binding) {
            TextViewCategory1.text = category.id.toString()
            TextViewCategory2.text = category.name
            TextViewCategory3.text = category.direction.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
        var view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_categories, parent, false)
        return CategoryHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun addCategory(category: Category) {
        categoryList.add(category)
        notifyDataSetChanged()
    }

}