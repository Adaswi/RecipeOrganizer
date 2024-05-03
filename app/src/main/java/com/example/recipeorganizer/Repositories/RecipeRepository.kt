package com.example.recipeorganizer.Repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.asLiveData
import com.example.recipeorganizer.DAOs.IngredientDao
import com.example.recipeorganizer.DAOs.RecipeDao
import com.example.recipeorganizer.Models.CategoriesToRecipes
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.IngredientsToRecipes
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeWithIngredientsAndCategories
import kotlinx.coroutines.flow.Flow

class RecipeRepository(private val recipeDao: RecipeDao, private val ingredientDao: IngredientDao) {

    val allRecipes: Flow<List<Recipe>> = recipeDao.getAll()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(recipe: Recipe, ingredients: List<Ingredient>, categories: List<Category>) {
        val id: Long = recipeDao.insert(recipe)
        for (ingredient in ingredients) {
            val ingredientId: Long = ingredientDao.insert(ingredient)
            recipeDao.insert(IngredientsToRecipes(0, ingredientId.toInt(), id.toInt()))
        }
        for (category in categories) {
            recipeDao.insert(CategoriesToRecipes(0, category.id, id.toInt()))
        }
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getById(id: Int): RecipeWithIngredientsAndCategories {
        return recipeDao.getById(id)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(recipe: Recipe) {
        recipeDao.delete(recipe)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(categoriesToRecipes: CategoriesToRecipes) {
        recipeDao.delete(categoriesToRecipes)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getCategoriesToRecipesByCategoryId(categoryId: Int): List<CategoriesToRecipes> {
        return recipeDao.getCategoriesToRecipesByCategoryId(categoryId)
    }
}