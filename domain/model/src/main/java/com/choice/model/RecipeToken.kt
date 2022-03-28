package com.choice.model

import com.google.gson.annotations.SerializedName

data class RecipeToken(
    @SerializedName("results")
    var results: List<Recipe>?
)