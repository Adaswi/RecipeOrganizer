package com.example.recipeorganizer

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.example.recipeorganizer.Models.CategoryModel
import com.example.recipeorganizer.Models.IngredientModel
import com.example.recipeorganizer.Models.MockData
import com.example.recipeorganizer.Models.RecipeModel
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

        val mockData: MockData = MockData()
        val recipes = mockData.recipes
        val categories = mockData.categories

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

        DisplayCategories(categories, chipGroup)
        DisplayRecipes(recipes, linearLayout)
    }

    fun DisplayCategories(categories: ArrayList<CategoryModel>, chipGroup: ChipGroup) {
        for (category in categories) {
            val chip = getLayoutInflater().inflate(R.layout.selectable_chip_layout, chipGroup, false)
            val chipItem = chip.findViewById<Chip>(R.id.chip)
            chipItem.text = category.name
            chipGroup.addView(chip)
        }
    }

    fun DisplayRecipes(recipes: ArrayList<RecipeModel>, linearLayout: LinearLayout) {
        for (recipe in recipes) {
            val materialCard = getLayoutInflater().inflate(R.layout.item_layout, linearLayout, false)
            val recipeText = materialCard.findViewById<TextView>(R.id.recipe_title_text)
            val prepTimeText = materialCard.findViewById<TextView>(R.id.prep_time_text)
            val image = materialCard.findViewById<ImageView>(R.id.image_small)
            val imgUri: Uri = Uri.parse("android.resource://" + packageName + "/drawable/" + recipe.imagePath + "_small")

            val time = recipe.preparationTime
            val text: String

            if (time > 59) {
                val hours = time/60
                val minutes = time%60
                text = "$hours h $minutes min"
            }
            else {
                text = "$time minutes"
            }

            recipeText.text = recipe.name
            prepTimeText.text = text
            image.setImageURI(imgUri)

            materialCard.setOnClickListener() {
                val openItem: Intent = Intent(this, ItemActivity::class.java)
                openItem.putExtra("recipeItemKey", recipe.id)
                startActivity(openItem)
            }

            linearLayout.addView(materialCard)
        }
    }
}