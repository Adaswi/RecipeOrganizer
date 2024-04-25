package com.example.recipeorganizer.ViewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipeorganizer.R

class AddIngredientViewHolder(itemView: View) : ViewHolder(itemView) {
    val text: TextView = itemView.findViewById(R.id.text)

    fun bind(id:Int?, name: String, amount: String?) {
        val textTogether = name + " " + amount
        text.text = textTogether

    }

    companion object {
        fun create(parent: ViewGroup): AddIngredientViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_ingredient_layout, parent, false)
            return AddIngredientViewHolder(view)
        }
    }
}

