package com.choice.local.entity

import androidx.room.*
import com.choice.local.mapping.Converter
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Recipes")
data class RecipeEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String? = "",
    val publisher: String? = "",
    val image: String? = "",
    val rating: Int? = 0,
    val sourceUrl: String? = "",
    val description: String? = "",
    val cookingInstructions: String? = "",
    @TypeConverters(Converter::class)
    val ingredients: List<String>? = listOf(),
    val dateAdded: String? = "",
    val dateUpdated: String? = "",
    val favorite: Boolean = false,
)