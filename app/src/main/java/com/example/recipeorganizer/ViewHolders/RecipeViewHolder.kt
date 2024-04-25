package com.example.recipeorganizer.ViewHolders

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.recipeorganizer.R

class RecipeViewHolder(itemView: View) : ViewHolder(itemView) {
    val recipeText = itemView.findViewById<TextView>(R.id.recipe_title_text)
    val prepTimeText = itemView.findViewById<TextView>(R.id.prep_time_text)
    val image = itemView.findViewById<ImageView>(R.id.image_small)

    fun bind(id: Int, name: String, preparationTime: Int, instructions: String, imagePath:String) {
        val imgUri: Uri = Uri.parse("android.resource://com.example.recipeorganizer/drawable/" + imagePath + "_small")

        val time = preparationTime
        val text: String

        if (time == 60) {
            text = "1 hour"
        }
        else if (time == 1) {
            text = "1 minute"
        }
        else if (time > 59 && time%60 != 0) {
            val hours = time/60
            val minutes = time%60
            text = "$hours h $minutes min"
        }
        else if (time > 59 && time%60 == 0){
            val hours = time/60
            text = "$hours hours"
        }
        else {
            text = "$time minutes"
        }

        recipeText.text = name
        prepTimeText.text = text
        image.setImageURI(imgUri)
    }

    companion object {
        fun create(parent: ViewGroup): RecipeViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout, parent, false)
            return RecipeViewHolder(view)
        }
    }
}

