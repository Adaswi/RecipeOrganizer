package com.example.recipeorganizer.Models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class RecipeWithIngredientsAndCategories(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn = RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID,
        entityColumn = RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID,
        associateBy = Junction(IngredientsToRecipes::class)
    )
    val ingredients: List<Ingredient>,
    @Relation(
        parentColumn = RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID,
        entityColumn = RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID,
        associateBy = Junction(CategoriesToRecipes::class)
    )
    val categories: List<Category>
) {
}