package com.example.recipeorganizer.Activities

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.MockData
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.R
import com.example.recipeorganizer.RecipeOrganizerApplication
import com.example.recipeorganizer.ViewModels.RecipeViewModel
import com.example.recipeorganizer.ViewModels.RecipeViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class ItemActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory((application as RecipeOrganizerApplication).recipeRepository)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val intent = getIntent()
        val recipeId: Int = intent.getIntExtra("recipeItemKey", 0)

        val materialToolbar = findViewById<MaterialToolbar>(R.id.topAppBarMaterials)
        val imageView = findViewById<ImageView>(R.id.image)
        val prepTimeText = findViewById<TextView>(R.id.prepTimeText)
        val linearLayout = findViewById<LinearLayout>(R.id.ingredientList)
        val recipeText = findViewById<TextView>(R.id.recipeText)
        val chipGroup = findViewById<ChipGroup>(R.id.chipGroup)

        val mockData: MockData = MockData()
        MainScope().launch {

            val recipeComplex = recipeViewModel.getById(recipeId).await()
            val recipe: Recipe = recipeComplex.recipe
            val ingredients: List<Ingredient> = recipeComplex.ingredients
            val categories: List<Category> = recipeComplex.categories

            DisplayRecipe(recipe, materialToolbar, imageView, prepTimeText, recipeText)
            DisplayIngredients(ingredients, linearLayout)
            DisplayCategories(categories, chipGroup)
        }
    }

    fun DisplayRecipe(recipe: Recipe, materialToolbar: MaterialToolbar, imageView: ImageView, prepTimeText: TextView, recipeText: TextView) {


        val imgUri: Uri = Uri.parse(baseContext.filesDir.toString() + "/foodImages/" + recipe.imagePath)
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

        recipeText.text = recipe.instructions
    }

    fun DisplayIngredients(ingredients: List<Ingredient>, linearLayout:LinearLayout) {
        for (ingredient in ingredients) {
            val ingredientText: TextView = getLayoutInflater().inflate(R.layout.ingredient_layout, linearLayout, false) as TextView
            ingredientText.text = "${ingredient.name} ${ingredient.amount.orEmpty()}"
            linearLayout.addView(ingredientText)
        }
    }

    fun DisplayCategories(categories: List<Category>, chipGroup:ChipGroup) {
        for (category in categories) {
            val chip: Chip = getLayoutInflater().inflate(R.layout.static_chip_layout, chipGroup, false) as Chip
            chip.text = category.name
            chipGroup.addView(chip)
        }
    }
}