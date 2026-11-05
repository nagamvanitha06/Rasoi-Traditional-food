package com.example.model

data class ShoppingItem(
    val id: String,
    val name: String,
    val quantity: String,
    val category: IngredientCategory,
    val isChecked: Boolean = false,
    val recipeSource: String? = null
)
