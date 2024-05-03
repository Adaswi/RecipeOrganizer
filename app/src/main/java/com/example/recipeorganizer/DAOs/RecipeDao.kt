package com.example.recipeorganizer.DAOs

import android.provider.BaseColumns
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.recipeorganizer.Models.CategoriesToRecipes
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.IngredientsToRecipes
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeWithIngredientsAndCategories
import com.example.recipeorganizer.Models.RecipeOrganizer
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM ${RecipeOrganizer.Recipe.TABLE_NAME}")
    fun getAll(): Flow<List<Recipe>>

    @Transaction
    @Query("SELECT * FROM ${RecipeOrganizer.Recipe.TABLE_NAME} WHERE ${RecipeOrganizer.Recipe.TABLE_NAME}.${RecipeOrganizer.Recipe.TABLE_NAME}${BaseColumns._ID} = (:id)")
    suspend fun getById(id: Int): RecipeWithIngredientsAndCategories

    @Transaction
    @Query("SELECT * FROM ${RecipeOrganizer.CategoryToRecipe.TABLE_NAME} WHERE ${RecipeOrganizer.CategoryToRecipe.TABLE_NAME}.${RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID} = (:categoryId)")
    suspend fun getCategoriesToRecipesByCategoryId(categoryId: Int): List<CategoriesToRecipes>

    @Insert
    suspend fun insert(recipe: Recipe):Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe, ingredients: List<Ingredient>)

    @Insert
    suspend fun insert(categoriesToRecipes: CategoriesToRecipes)

    @Delete
    suspend fun delete(vararg categoriesToRecipes: CategoriesToRecipes)

    @Insert
    suspend fun insert(ingredientsToRecipes: IngredientsToRecipes)

    @Delete
    suspend fun delete(vararg ingredientsToRecipes: IngredientsToRecipes)

    @Delete
    suspend fun delete(vararg recipes: Recipe)

    @Query("DELETE FROM ${RecipeOrganizer.Recipe.TABLE_NAME}")
    suspend fun deleteAll()
}