package com.example.recipeorganizer.DAOs

import android.provider.BaseColumns
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
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

    @Insert
    suspend fun insert(recipe: Recipe)

    @Delete
    suspend fun delete(vararg recipes: Recipe)

    @Query("DELETE FROM ${RecipeOrganizer.Recipe.TABLE_NAME}")
    suspend fun deleteAll()
}