package com.example.recipeorganizer.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.recipeorganizer.Models.CategoriesToRecipes
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeWithIngredientsAndCategories
import com.example.recipeorganizer.Repositories.RecipeRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: RecipeRepository) : ViewModel() {
    val allRecipes: LiveData<List<Recipe>> = repository.allRecipes.asLiveData()

    fun insert(recipe: Recipe) = viewModelScope.launch {
        repository.insert(recipe)
    }

    fun insert(recipe: Recipe, ingredients: List<Ingredient>, categories: List<Category>) = viewModelScope.launch {
        repository.insert(recipe, ingredients, categories)
    }

    fun getById(id: Int)  = viewModelScope.async {
        repository.getById(id)
    }

    fun delete(categoryToRecipe: CategoriesToRecipes) = viewModelScope.async {
        repository.delete(categoryToRecipe)
    }

    fun getCategoriesToRecipesByCategoryId(categoryId: Int) = viewModelScope.async {
        repository.getCategoriesToRecipesByCategoryId(categoryId)
    }
}