package com.example.recipeorganizer.Models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class IngredientsToRecipes(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RecipeOrganizer.IngredientToRecipe.TABLE_NAME+ BaseColumns._ID) val id: Int,
    @ColumnInfo(name = RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID) val ingredientId: Int,
    @ColumnInfo(name = RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID) val recipeId: Int
) {
}