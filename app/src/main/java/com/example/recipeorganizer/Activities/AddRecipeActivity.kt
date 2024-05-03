package com.example.recipeorganizer.Activities

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.WorkerThread
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeorganizer.Adapters.AddCategoryAdapter
import com.example.recipeorganizer.Adapters.AddIngredientAdapter
import com.example.recipeorganizer.ImageSaver
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.R
import com.example.recipeorganizer.RecipeOrganizerApplication
import com.example.recipeorganizer.ViewModels.CategoryViewModel
import com.example.recipeorganizer.ViewModels.CategoryViewModelFactory
import com.example.recipeorganizer.ViewModels.RecipeViewModel
import com.example.recipeorganizer.ViewModels.RecipeViewModelFactory
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import java.util.UUID


class AddRecipeActivity : AppCompatActivity() {

    private val recipeViewModel: RecipeViewModel by viewModels {
        RecipeViewModelFactory((application as RecipeOrganizerApplication).recipeRepository)
    }

    private val categoryViewModel: CategoryViewModel by viewModels {
        CategoryViewModelFactory((application as RecipeOrganizerApplication).categoryRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val nameInput: TextInputEditText = findViewById(R.id.nameInput)
        val hourInput: TextInputEditText = findViewById(R.id.hourInput)
        val minuteInput: TextInputEditText = findViewById(R.id.minuteInput)
        val instructions: TextInputEditText = findViewById(R.id.instructionInput)

        val ingredients: MutableList<Ingredient> = mutableListOf()
        val categories: MutableList<Category> = mutableListOf()
        val ingredientRecyclerView: RecyclerView = findViewById(R.id.ingredientsRecycleView)
        val categoryRecyclerView: RecyclerView = findViewById(R.id.categoryRecyclerView)
        val ingredientAdapter = AddIngredientAdapter(ingredients)
        val categoryAdapter = AddCategoryAdapter(categories)
        val ingredientIconButton: Button = findViewById(R.id.ingredientIconButton)
        val ingredientNameInput: TextInputEditText = findViewById(R.id.ingredientNameInput)
        val ingredientAmountInput: TextInputEditText = findViewById(R.id.ingredientAmountInput)
        val imageButton: ExtendedFloatingActionButton = findViewById(R.id.extended_fab)
        val topAppBar: MaterialToolbar = findViewById(R.id.topAppBarMaterials)
        var imgUri: Uri = Uri.EMPTY
        var image: Bitmap? = null

        ingredientRecyclerView.adapter = ingredientAdapter
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this)

        categoryRecyclerView.adapter = categoryAdapter
        categoryRecyclerView.layoutManager = LinearLayoutManager(this)

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                imgUri = uri
                @WorkerThread
                image = ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, uri))
            }
        }

        categoryViewModel.allCategories.observe(this, Observer { categories ->
            // Update the cached copy of the words in the adapter.
            categories?.let {
                categoryAdapter.submitList(it)
            }
        })

        ingredientIconButton.setOnClickListener {
            if (ingredientAmountInput.text.isNullOrEmpty()) {
                Toast.makeText(this, R.string.no_ingredient_name, Toast.LENGTH_SHORT).show()
            }
            ingredients.add(Ingredient(
                0,
                ingredientNameInput.text.toString(),
                ingredientAmountInput.text.toString()
            ))
            ingredientNameInput.text?.clear()
            ingredientAmountInput.text?.clear()
            ingredientNameInput.clearFocus()
            ingredientAmountInput.clearFocus()
            ingredientAdapter.submitList(ingredients)
            ingredientAdapter.notifyItemInserted(ingredients.size-1)
        }

        imageButton.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        topAppBar.setNavigationOnClickListener {
            // Handle navigation icon press
        }

        topAppBar.setNavigationOnClickListener {
            finish()
        }

        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.confirm -> {
                    if (nameInput.text.isNullOrEmpty())
                    {
                        Toast.makeText(this, R.string.no_recipe_name, Toast.LENGTH_SHORT).show()
                    }
                    else if (hourInput.text.isNullOrEmpty() && minuteInput.text.isNullOrEmpty())
                    {
                        Toast.makeText(this, R.string.no_prep_time, Toast.LENGTH_SHORT).show()
                    }
                    else if (instructions.text.isNullOrEmpty())
                    {
                        Toast.makeText(this, R.string.no_instructions, Toast.LENGTH_SHORT).show()
                    }
                    else if (ingredients.isEmpty())
                    {
                        Toast.makeText(this, R.string.no_ingredients, Toast.LENGTH_SHORT).show()
                    }
                    else if (imgUri == Uri.EMPTY || image == null)
                    {
                        Toast.makeText(this, R.string.no_image, Toast.LENGTH_SHORT).show()
                    }
                    else
                    {
                        Log.d("ClickItem", "Confirm clicked")
                        val fileName = UUID.randomUUID().toString()

                        ImageSaver(this)
                            .setFileName(fileName)
                            .setExternal(false)
                            .setDirectory("foodImages")
                            .save(image!!)

                        val hours: Int
                        val minutes: Int

                        if (hourInput.text.isNullOrEmpty()){
                            hours = 0
                            minutes = (minuteInput.text.toString()).toInt()
                        }
                        else if (minuteInput.text.isNullOrEmpty()) {
                            hours = (hourInput.text.toString()).toInt()
                            minutes = 0
                        }
                        else {
                            hours = (hourInput.text.toString()).toInt()
                            minutes = (minuteInput.text.toString()).toInt()
                        }

                        val time = hours*60 + minutes

                        val recipe = Recipe(0, nameInput.text.toString(), time, instructions.text.toString(), fileName)

                        recipeViewModel.insert(recipe, ingredients, categories)

                        finish()
                    }

                    true
                }
                else -> false
            }
        }
    }
}