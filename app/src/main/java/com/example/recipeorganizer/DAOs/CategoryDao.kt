package com.example.recipeorganizer.DAOs

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.recipeorganizer.Models.Category
import com.example.recipeorganizer.Models.Recipe
import com.example.recipeorganizer.Models.RecipeOrganizer
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM ${RecipeOrganizer.Category.TABLE_NAME}")
    fun getAll(): Flow<List<Category>>

    @Insert
    suspend fun insert(category: Category)

    @Delete
    suspend fun delete(vararg category:Category)

    @Query("DELETE FROM ${RecipeOrganizer.Category.TABLE_NAME}")
    suspend fun deleteAll()
}