package com.example.recipeorganizer.Adapters

import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.R
import com.example.recipeorganizer.ViewHolders.AddCategoryViewHolder

class AddCategoryAdapter constructor(categories: MutableList<Category>) : ListAdapter<Category, AddCategoryViewHolder>(CategoriesComparator()) {

    val categories = categories

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddCategoryViewHolder {
        return AddCategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AddCategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id, current.name)
        val checkBox = holder.itemView.findViewById<CheckBox>(R.id.checkbox)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                categories.add(current)
            } else {
                categories.remove(current)
            }
        }

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