package com.example.recipeorganizer.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Repositories.CategoryRepository
import com.example.recipeorganizer.Repositories.IngredientRepository
import java.lang.IllegalArgumentException

class IngredientViewModelFactory(private val repository: IngredientRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(IngredientViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return IngredientViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}