package com.example.recipeorganizer.Models

data class RecipeModel(
    val id: Int,
    val name: String,
    val preparationTime: Int,
    val instructions: String,
    val imagePath: String
) {

}