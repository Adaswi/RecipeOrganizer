package com.example.recipeorganizer

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.provider.ContactsContract.Intents.Insert
import com.example.recipeorganizer.Models.CategoryModel
import com.example.recipeorganizer.Models.RecipeModel
import java.util.Locale.Category

class DatabaseManager(context: Context) {
    val dbHelper = DatabaseHelper(context)
    val db = dbHelper.writableDatabase

    //inserts
    fun InsertCategory(name: String): Long? {
        val values = ContentValues().apply {
            put(RecipeOrganizer.Category.COLUMN_NAME_CATEGORY_NAME, name)
        }

        val id = db?.insert(RecipeOrganizer.Category.TABLE_NAME, null, values)
        return id
    }

    fun InsertIngredient(name: String, amount:String?): Long? {
        val values = ContentValues().apply {
            put(RecipeOrganizer.Ingredient.COLUMN_NAME_INGREDIENT_NAME, name)
            put(RecipeOrganizer.Ingredient.COLUMN_NAME_AMOUNT,amount)
        }

        val id = db?.insert(RecipeOrganizer.Ingredient.TABLE_NAME, null, values)
        return id
    }

    fun InsertRecipe(
        name: String,
        preparationTime: Int,
        instructions: String,
        imagePath: String): Long? {
        val values = ContentValues().apply {
            put(RecipeOrganizer.Recipe.COLUMN_NAME_RECIPE_NAME, name)
            put(RecipeOrganizer.Recipe.COLUMN_NAME_PREP_TIME, preparationTime)
            put(RecipeOrganizer.Recipe.COLUMN_NAME_INSTRUCTIONS, instructions)
            put(RecipeOrganizer.Recipe.COLUMN_NAME_IMAGE_PATH, imagePath)
        }

        val id = db?.insert(RecipeOrganizer.Recipe.TABLE_NAME, null, values)
        return id
    }

    fun InsertIngredientToRecipe(ingredientId: Int, recipeId: Int): Long? {
        val values = ContentValues().apply {
            put(RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID, ingredientId)
            put(RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID, recipeId)
        }

        val id = db?.insert(RecipeOrganizer.IngredientToRecipe.TABLE_NAME, null, values)
        return id
    }

    fun InsertCategoryToRecipe(categoryId: Int, recipeId: Int): Long? {
        val values = ContentValues().apply {
            put(RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID, categoryId)
            put(RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID, recipeId)
        }

        val id = db?.insert(RecipeOrganizer.CategoryToRecipe.TABLE_NAME, null, values)
        return id
    }

    //fetches

    fun fetchCategories(): ArrayList<CategoryModel> {
        val cursor = db.query(
            RecipeOrganizer.Category.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)

        val categories = ArrayList<CategoryModel>()
        with(cursor) {
            while (moveToNext()) {
                val category = CategoryModel(
                    getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                    getString(getColumnIndexOrThrow(RecipeOrganizer.Category.COLUMN_NAME_CATEGORY_NAME))
                )
                categories.add(category)
            }
        }
        return categories
    }

    fun fetchRecipes(): ArrayList<RecipeModel>{
        val cursor = db.query(
            RecipeOrganizer.Recipe.TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null)

        val recipes = ArrayList<RecipeModel>()
        with(cursor) {
            while (moveToNext()) {
                val recipe = RecipeModel(
                    getInt(getColumnIndexOrThrow(BaseColumns._ID)),
                    getString(getColumnIndexOrThrow(RecipeOrganizer.Recipe.COLUMN_NAME_RECIPE_NAME)),
                    getInt(getColumnIndexOrThrow(RecipeOrganizer.Recipe.COLUMN_NAME_PREP_TIME)),
                    getString(getColumnIndexOrThrow(RecipeOrganizer.Recipe.COLUMN_NAME_INSTRUCTIONS)),
                    getString(getColumnIndexOrThrow(RecipeOrganizer.Recipe.COLUMN_NAME_IMAGE_PATH))
                )
                recipes.add(recipe)
            }
        }
        return recipes
    }
}