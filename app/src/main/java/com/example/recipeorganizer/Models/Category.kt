package com.example.recipeorganizer.Models

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = RecipeOrganizer.Category.TABLE_NAME+BaseColumns._ID) val id: Int,
    @ColumnInfo(name = RecipeOrganizer.Category.COLUMN_NAME_CATEGORY_NAME) val name: String,
) {
}