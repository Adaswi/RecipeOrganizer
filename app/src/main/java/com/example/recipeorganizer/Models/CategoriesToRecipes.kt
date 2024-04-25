package com.example.recipeorganizer.Models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = [RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID, RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID])
data class CategoriesToRecipes(
    @ColumnInfo(name = RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID)val categoryId: Int,
    @ColumnInfo(name = RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID)val recipeId: Int
) {
}