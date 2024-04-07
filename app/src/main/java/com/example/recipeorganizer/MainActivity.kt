package com.example.recipeorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.core.widget.NestedScrollView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linearLayout = findViewById<LinearLayout>(R.id.linearLayout)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)
        val coordinatorLayout = findViewById<CoordinatorLayout>(R.id.cl_overlay)
        val addButton = findViewById<FloatingActionButton>(R.id.floating_action_button_add)
        val backButton = findViewById<FloatingActionButton>(R.id.floating_action_button_back)
        val addRecipeButton = findViewById<Button>(R.id.elevatedButton_addRecipe)
        val recipeNames = listOf("Spaghetti carbonara", "Shrimp soup", "Chicken Sandwich", "a", "a", "a", "a", "a", "a", "a")
        val categoryNames = listOf("Breakfast", "Lunch", "Dinner", "Easter", "Christmas", "Baby")
        val currentName = R.string.recipe_name

        for (category in categoryNames) {
            val chip = getLayoutInflater().inflate(R.layout.chip_layout, chipGroup, false);
            chipGroup.addView(chip)
        }

        for (name in recipeNames) {
            val materialCard = getLayoutInflater().inflate(R.layout.item_layout, linearLayout, false);
            linearLayout.addView(materialCard)
        }

        addButton.setOnClickListener {
            coordinatorLayout.visibility = View.VISIBLE
            coordinatorLayout.animate().alpha(1f)
            addButton.visibility = View.GONE
        }

        backButton.setOnClickListener {
            addButton.visibility = View.VISIBLE
            coordinatorLayout.animate().alpha(0f)
            coordinatorLayout.visibility = View.GONE
        }
    }
}