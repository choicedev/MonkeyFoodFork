package com.choice.local.mapping

import com.choice.local.entity.RecipeEntity
import com.choice.model.Recipe

fun RecipeEntity.toDomain() = Recipe(
    id,
    title,
    publisher,
    image,
    rating,
    sourceUrl,
    description,
    cookingInstructions,
    ingredients,
    dateAdded,
    dateUpdated,
    favorite
)

fun Recipe.toEntity() = RecipeEntity(
    id,
    title,
    publisher,
    image,
    rating,
    sourceUrl,
    description,
    cookingInstructions,
    ingredients,
    dateAdded,
    dateUpdated,
    favorite
)