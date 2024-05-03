package com.example.recipeorganizer.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import com.example.recipeorganizer.DAOs.CategoryDao
import com.example.recipeorganizer.DAOs.IngredientDao
import com.example.recipeorganizer.DAOs.RecipeDao
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeWithIngredientsAndCategories
import kotlinx.coroutines.flow.Flow

class IngredientRepository(private val ingredientDao: IngredientDao) {

    val allIngredients: Flow<List<Ingredient>> = ingredientDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(ingredient: Ingredient) {
        ingredientDao.insert(ingredient)
    }
}