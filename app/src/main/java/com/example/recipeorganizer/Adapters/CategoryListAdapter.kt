package com.example.recipeorganizer.Adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.ViewHolders.CategoryViewHolder

class CategoryListAdapter : ListAdapter<Category, CategoryViewHolder>(CategoriesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id, current.name)
    }

    class CategoriesComparator : DiffUtil.ItemCallback<Category>() {
        override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
            return oldItem.id == newItem.id &&
            oldItem.name == newItem.name
        }
    }
}