package com.example.recipeorganizer.Adapters

import android.content.Intent
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipeorganizer.Activities.ItemActivity
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.ViewHolders.RecipeViewHolder

class RecipeListAdapter : ListAdapter<Recipe, RecipeViewHolder>(RecipesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.id, current.name, current.preparationTime, current.instructions, current.imagePath)
        holder.itemView.setOnClickListener() {
            val openItem: Intent = Intent(it.context, ItemActivity::class.java)
            openItem.putExtra("recipeItemKey", current.id)
            ContextCompat.startActivity(it.context, openItem, null)
        }
    }

    class RecipesComparator : DiffUtil.ItemCallback<Recipe>() {
        override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
            return oldItem.name == newItem.name &&
            oldItem.preparationTime == newItem.preparationTime &&
            oldItem.instructions == newItem.instructions &&
            oldItem.imagePath == newItem.imagePath
        }
    }
}