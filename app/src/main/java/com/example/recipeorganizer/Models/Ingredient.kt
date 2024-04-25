package com.example.recipeorganizer.Models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Ingredient(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RecipeOrganizer.Ingredient.TABLE_NAME+BaseColumns._ID) val id: Int,
    @ColumnInfo(name = RecipeOrganizer.Ingredient.COLUMN_NAME_INGREDIENT_NAME) val name: String,
    @ColumnInfo(name = RecipeOrganizer.Ingredient.COLUMN_NAME_AMOUNT) val amount: String?
) {
}