package com.example.recipeorganizer.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import com.example.recipeorganizer.DAOs.RecipeDao
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeWithIngredientsAndCategories
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao) {

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAll()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getById(id: Int): RecipeWithIngredientsAndCategories {
        return recipeDao.getById(id)
    }
}