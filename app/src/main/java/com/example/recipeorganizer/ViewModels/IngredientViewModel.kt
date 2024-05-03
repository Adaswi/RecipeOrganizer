package com.example.recipeorganizer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeorganizer.Repositories.CategoryRepository
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Repositories.IngredientRepository
import kotlinx.coroutines.launch

class IngredientViewModel(private val repository: IngredientRepository) : ViewModel() {
    val allIngredients: LiveData<List<Ingredient>> = repository.allIngredients.asLiveData()

    fun insert(ingredient: Ingredient) = viewModelScope.launch {
        repository.insert(ingredient)
    }
}