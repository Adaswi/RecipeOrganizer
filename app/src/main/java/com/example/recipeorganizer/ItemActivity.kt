package com.example.recipeorganizer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeorganizer.Models.CategoryModel
import com.example.recipeorganizer.Models.IngredientModel
import com.example.recipeorganizer.Models.MockData
import com.example.recipeorganizer.Models.RecipeModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.card.MaterialCardView
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_activity)

        val intent = getIntent()
        val recipeId: Int = intent.getIntExtra("recipeItemKey", 0)

        val materialToolbar = findViewById<MaterialToolbar>(R.id.topAppBarMaterials)
        val imageView = findViewById<ImageView>(R.id.image)
        val prepTimeText = findViewById<TextView>(R.id.prepTimeText)
        val linearLayout = findViewById<LinearLayout>(R.id.ingredientList)
        val recipeText = findViewById<TextView>(R.id.recipeText)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)

        val mockData: MockData = MockData()
        val recipe: RecipeModel = mockData.recipes[recipeId-1]
        val ingredients: ArrayList<IngredientModel> = mockData.ingredients
        val categories: ArrayList<CategoryModel> = mockData.categories


        val imgUri: Uri = Uri.parse("android.resource://" + packageName + "/drawable/" + recipe.imagePath)
        val time = recipe.preparationTime

        materialToolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        materialToolbar.title = recipe.name

        imageView.setImageURI(imgUri)

        if (time > 59) {
            val hours = time/60
            val minutes = time%60
            prepTimeText.text = "${prepTimeText.text}: $hours h $minutes min"
        }
        else {
            val text = getString(R.string.prep_time_title)
            prepTimeText.text = "${text}: $time minutes"
        }

        for (ingredient in ingredients) {
            val ingredientText: TextView = getLayoutInflater().inflate(R.layout.ingredient_layout, linearLayout, false) as TextView
            ingredientText.text = "${ingredient.name} ${ingredient.amount.orEmpty()}"
            linearLayout.addView(ingredientText)
        }

        recipeText.text = recipe.instructions

        for (category in categories) {
            val chip: Chip = getLayoutInflater().inflate(R.layout.static_chip_layout, chipGroup, false) as Chip
            chip.text = category.name
            chipGroup.addView(chip)
        }
    }
}