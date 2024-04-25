package com.example.recipeorganizer.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recipeorganizer.Adapters.IngredientAdapter
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.R
import com.google.android.material.textfield.TextInputEditText

class AddRecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        val nameInput: TextInputEditText = findViewById(R.id.nameInput)
        val hourInput: TextInputEditText = findViewById(R.id.hourInput)
        val minuteInput: TextInputEditText = findViewById(R.id.minuteInput)
        val instructions: TextInputEditText = findViewById(R.id.instructionInput)

        val ingredients: MutableList<Ingredient> = mutableListOf()
        val ingredientRecyclerView: RecyclerView = findViewById(R.id.ingredientsRecycleView)
        val adapter = IngredientAdapter(ingredients)
        val ingredientIconButton: Button = findViewById(R.id.ingredientIconButton)
        val ingredientNameInput: TextInputEditText = findViewById(R.id.ingredientNameInput)
        val ingredientAmountInput: TextInputEditText = findViewById(R.id.ingredientAmountInput)


        ingredientRecyclerView.adapter = adapter
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this)

        ingredientIconButton.setOnClickListener {
            ingredients.add(Ingredient(
                0,
                ingredientNameInput.text.toString(),
                ingredientAmountInput.text.toString()
            ))
            adapter.submitList(ingredients)
            adapter.notifyItemInserted(ingredients.size-1)
        }
    }
}