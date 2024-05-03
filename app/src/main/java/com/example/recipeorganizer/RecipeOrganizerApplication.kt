package com.example.recipeorganizer

import android.app.Application
import com.example.recipeorganizer.Repositories.CategoryRepository
import com.example.recipeorganizer.Repositories.IngredientRepository
import com.example.recipeorganizer.Repositories.RecipeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class RecipeOrganizerApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { RecipeOrganizerDatabase.getDatabase(this, applicationScope) }
    val recipeRepository by lazy { RecipeRepository(database.recipeDao(), database.ingredientDao()) }
    val categoryRepository by lazy { CategoryRepository(database.categoryDao()) }
    val ingredientRepository by lazy { IngredientRepository(database.ingredientDao()) }
}