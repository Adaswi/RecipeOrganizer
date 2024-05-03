package com.example.recipeorganizer.Models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoriesToRecipes(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RecipeOrganizer.CategoryToRecipe.TABLE_NAME+ BaseColumns._ID) val id: Int,
    @ColumnInfo(name = RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID)val categoryId: Int,
    @ColumnInfo(name = RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID)val recipeId: Int
) {
}