package com.example.recipeorganizer.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Ingredient
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeOrganizer
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientDao {
    @Query("SELECT * FROM ${RecipeOrganizer.Ingredient.TABLE_NAME}")
    fun getAll(): Flow<List<Ingredient>>

    @Insert
    suspend fun insert(ingredient: Ingredient): Long

    @Insert
    suspend fun insert(ingredients: List<Ingredient>): List<Long>

    @Delete
    suspend fun delete(vararg ingredient: Ingredient)

    @Query("DELETE FROM ${RecipeOrganizer.Ingredient.TABLE_NAME}")
    suspend fun deleteAll()
}