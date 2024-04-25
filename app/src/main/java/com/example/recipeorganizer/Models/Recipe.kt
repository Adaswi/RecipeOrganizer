package com.example.recipeorganizer.Models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RecipeOrganizer.Recipe.TABLE_NAME+BaseColumns._ID) val id: Int,
    @ColumnInfo(name = RecipeOrganizer.Recipe.COLUMN_NAME_RECIPE_NAME) val name: String,
    @ColumnInfo(name = RecipeOrganizer.Recipe.COLUMN_NAME_PREP_TIME) val preparationTime: Int,
    @ColumnInfo(name = RecipeOrganizer.Recipe.COLUMN_NAME_INSTRUCTIONS) val instructions: String,
    @ColumnInfo(name = RecipeOrganizer.Recipe.COLUMN_NAME_IMAGE_PATH) val imagePath: String
) {

}