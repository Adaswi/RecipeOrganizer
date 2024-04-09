package com.example.recipeorganizer

import android.provider.BaseColumns

object RecipeOrganizer {
    object Recipe : BaseColumns {
        const val TABLE_NAME = "recipes"
        const val COLUMN_NAME_RECIPE_NAME = "name"
        const val COLUMN_NAME_PREP_TIME = "preparation_time"
        const val COLUMN_NAME_INSTRUCTIONS = "instructions"
        const val COLUMN_NAME_IMAGE_PATH = "image_path"
    }

    object Ingredient : BaseColumns {
        const val TABLE_NAME = "ingredients"
        const val COLUMN_NAME_INGREDIENT_NAME = "name"
        const val COLUMN_NAME_AMOUNT = "amount"
    }

    object Category : BaseColumns {
        const val TABLE_NAME = "categories"
        const val COLUMN_NAME_CATEGORY_NAME = "name"
    }

    object IngredientToRecipe : BaseColumns {
        const val TABLE_NAME = "ingredients_to_recipes"
        const val COLUMN_NAME_INGREDIENT_ID = "ingredient_id"
        const val COLUMN_NAME_RECIPE_ID = "recipe_id"
    }

    object CategoryToRecipe : BaseColumns {
        const val TABLE_NAME = "categories_to_recipes"
        const val COLUMN_NAME_CATEGORY_ID = "category_id"
        const val COLUMN_NAME_RECIPE_ID = "recipe_id"
    }
}