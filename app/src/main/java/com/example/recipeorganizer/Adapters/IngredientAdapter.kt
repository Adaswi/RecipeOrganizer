package com.example.recipeorganizer.Adapters


import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.R
import com.example.recipeorganizer.ViewHolders.AddIngredientViewHolder

class IngredientAdapter(ingredients: MutableList<Ingredient>): ListAdapter<Ingredient, AddIngredientViewHolder>(
    IngredientsComparator()
) {

    val ingredients:MutableList<Ingredient> = ingredients
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddIngredientViewHolder {
        return AddIngredientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AddIngredientViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(null, current.name, current.amount)
        holder.itemView.findViewById<Button>(R.id.iconButton).setOnClickListener {
            this.ingredients.removeAt(position)
            this.notifyItemRemoved(position)
        }
    }

    class IngredientsComparator : DiffUtil.ItemCallback<Ingredient>() {
        override fun areItemsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Ingredient, newItem: Ingredient): Boolean {
            return oldItem.id == newItem.id &&
            oldItem.name == newItem.name &&
            oldItem.amount == newItem.amount
        }
    }

}