package com.example.recipeorganizer
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_CATEGORIES)
        db.execSQL(SQL_CREATE_INGREDIENTS)
        db.execSQL(SQL_CREATE_RECIPES)
        db.execSQL(SQL_CREATE_INGREDIENTS_TO_RECIPES)
        db.execSQL(SQL_CREATE_CATEGORIES_TO_RECIPES)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_CATEGORIES_TO_RECIPES)
        db.execSQL(SQL_DELETE_INGREDIENTS_TO_RECIPES)
        db.execSQL(SQL_DELETE_RECIPES)
        db.execSQL(SQL_DELETE_INGREDIENTS)
        db.execSQL(SQL_DELETE_CATEGORIES)
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "RecipeOrganizer.db"

        const val SQL_CREATE_CATEGORIES =
            "CREATE TABLE ${RecipeOrganizer.Category.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${RecipeOrganizer.Category.COLUMN_NAME_CATEGORY_NAME} TEXT NOT NULL)"
        const val SQL_CREATE_INGREDIENTS =
            "CREATE TABLE ${RecipeOrganizer.Ingredient.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${RecipeOrganizer.Ingredient.COLUMN_NAME_INGREDIENT_NAME} TEXT NOT NULL," +
                    "${RecipeOrganizer.Ingredient.COLUMN_NAME_AMOUNT} TEXT)"
        const val SQL_CREATE_RECIPES =
            "CREATE TABLE ${RecipeOrganizer.Recipe.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${RecipeOrganizer.Recipe.COLUMN_NAME_RECIPE_NAME} TEXT NOT NULL," +
                    "${RecipeOrganizer.Recipe.COLUMN_NAME_PREP_TIME} INTEGER NOT NULL," +
                    "${RecipeOrganizer.Recipe.COLUMN_NAME_INSTRUCTIONS} TEXT NOT NULL," +
                    "${RecipeOrganizer.Recipe.COLUMN_NAME_IMAGE_PATH} TEXT NOT NULL)"
        const val SQL_CREATE_INGREDIENTS_TO_RECIPES =
            "CREATE TABLE ${RecipeOrganizer.IngredientToRecipe.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID} INTEGER NOT NULL," +
                    "${RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID} INTEGER NOT NULL," +
                    "FOREIGN KEY(${RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_INGREDIENT_ID}) REFERENCES ${RecipeOrganizer.Ingredient.TABLE_NAME}(${BaseColumns._ID})," +
                    "FOREIGN KEY(${RecipeOrganizer.IngredientToRecipe.COLUMN_NAME_RECIPE_ID}) REFERENCES ${RecipeOrganizer.Recipe.TABLE_NAME}(${BaseColumns._ID}))"
        const val SQL_CREATE_CATEGORIES_TO_RECIPES =
            "CREATE TABLE ${RecipeOrganizer.CategoryToRecipe.TABLE_NAME} (" +
                    "${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "${RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID} INTEGER NOT NULL," +
                    "${RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID} INTEGER NOT NULL," +
                    "FOREIGN KEY(${RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_CATEGORY_ID}) REFERENCES ${RecipeOrganizer.Ingredient.TABLE_NAME}(${BaseColumns._ID})," +
                    "FOREIGN KEY(${RecipeOrganizer.CategoryToRecipe.COLUMN_NAME_RECIPE_ID}) REFERENCES ${RecipeOrganizer.Recipe.TABLE_NAME}(${BaseColumns._ID}))"

        const val SQL_DELETE_CATEGORIES_TO_RECIPES = "DROP TABLE IF EXISTS ${RecipeOrganizer.CategoryToRecipe.TABLE_NAME}"
        const val SQL_DELETE_INGREDIENTS_TO_RECIPES = "DROP TABLE IF EXISTS ${RecipeOrganizer.IngredientToRecipe.TABLE_NAME}"
        const val SQL_DELETE_CATEGORIES = "DROP TABLE IF EXISTS ${RecipeOrganizer.Category.TABLE_NAME}"
        const val SQL_DELETE_INGREDIENTS = "DROP TABLE IF EXISTS ${RecipeOrganizer.Ingredient.TABLE_NAME}"
        const val SQL_DELETE_RECIPES = "DROP TABLE IF EXISTS ${RecipeOrganizer.Recipe.TABLE_NAME}"
    }
}