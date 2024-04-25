package com.example.recipeorganizer.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import com.example.recipeorganizer.DAOs.CategoryDao
import com.example.recipeorganizer.DAOs.RecipeDao
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeWithIngredientsAndCategories
import kotlinx.coroutines.flow.Flow

class CategoryRepository(private val categoryDao: CategoryDao) {

    val allCategories: Flow<List<Category>> = categoryDao.getAll()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }
}