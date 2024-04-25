package com.example.recipeorganizer.Models

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = [RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID, RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID])
data class IngredientsToRecipes(
    @ColumnInfo(name = RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID) val ingredientId: Int,
    @ColumnInfo(name = RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID) val recipeId: Int
) {
}