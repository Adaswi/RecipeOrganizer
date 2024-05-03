package com.example.recipeorganizer.ViewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipeorganizer.Activities.AddRecipeActivity
import com.example.recipeorganizer.R
import com.google.android.material.chip.Chip

class AddCategoryViewHolder(itemView: View) : ViewHolder(itemView) {
    val categoryLinearLayout: LinearLayout = itemView.findViewById(R.id.categoryLinear)

    fun bind(id: Int, name: String) {
        val categoryText: TextView= itemView.findViewById(R.id.text)
        val checkBox: CheckBox = itemView.findViewById(R.id.checkbox)

        categoryText.text = name
    }

    companion object {
        fun create(parent: ViewGroup): AddCategoryViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_category_layout, parent, false)
            return AddCategoryViewHolder(view)
        }
    }
}

