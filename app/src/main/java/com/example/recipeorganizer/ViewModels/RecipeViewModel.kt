package com.example.recipeorganizer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Repositories.RecipeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    val allRecipes: LiveData<List<Recipe>> = repository.allRecipes.asLiveData()

    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }

    fun getById(id: Int)  = viewModelScope.async {
        repository.getById(id)
    }
}