package com.example.recipeorganizer.ViewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipeorganizer.R
import com.google.android.material.chip.Chip

class ManageCategoryViewHolder(itemView: View) : ViewHolder(itemView) {
    val text: TextView = itemView.findViewById(R.id.text)

    fun bind(id: Int, name: String) {
        text.text = name
    }

    companion object {
        fun create(parent: ViewGroup): ManageCategoryViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.add_ingredient_layout, parent, false)
            return ManageCategoryViewHolder(view)
        }
    }
}

