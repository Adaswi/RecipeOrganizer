package com.example.recipeorganizer.Adapters

import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipeorganizer.Models.CategoriesToRecipes
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.R
import com.example.recipeorganizer.RecipeOrganizerApplication
import com.example.recipeorganizer.ViewHolders.AddCategoryViewHolder
import com.example.recipeorganizer.ViewHolders.ManageCategoryViewHolder
import com.example.recipeorganizer.ViewModels.CategoryViewModel
import com.example.recipeorganizer.ViewModels.CategoryViewModelFactory
import com.example.recipeorganizer.ViewModels.RecipeViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ManageCategoryAdapter constructor(categoryViewModel: CategoryViewModel, recipeViewModel: RecipeViewModel) : ListAdapter<Category, ManageCategoryViewHolder>(CategoriesComparator()) {

    val categoryViewModel: CategoryViewModel = categoryViewModel
    val recipeViewModel: RecipeViewModel = recipeViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ManageCategoryViewHolder {
        return ManageCategoryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ManageCategoryViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id, current.name)
        holder.itemView.findViewById<Button>(R.id.iconButton).setOnClickListener {
            MainScope().launch {
                val categoriesToRecipes: List<CategoriesToRecipes> = recipeViewModel.getCategoriesToRecipesByCategoryId(current.id).await()
                for (categoryToRecipe in categoriesToRecipes){
                    recipeViewModel.delete(categoryToRecipe)
                }
                categoryViewModel.delete(current)
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