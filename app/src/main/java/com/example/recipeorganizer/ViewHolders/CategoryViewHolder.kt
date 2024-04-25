package com.example.recipeorganizer.ViewHolders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipeorganizer.R
import com.google.android.material.chip.Chip

class CategoryViewHolder(itemView: View) : ViewHolder(itemView) {
    val categoryChip: Chip = itemView.findViewById(R.id.chip)

    fun bind(id: Int, name: String) {
        val chip:Chip = itemView.findViewById(R.id.chip)
        chip.text = name
    }

    companion object {
        fun create(parent: ViewGroup): CategoryViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.selectable_chip_layout, parent, false)
            return CategoryViewHolder(view)
        }
    }
}

