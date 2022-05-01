package com.choice.remote

import com.choice.core.remote.Url
import com.choice.model.Recipe
import com.choice.model.RecipeToken
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiFoodFork {


    @GET(Url.GET_RECIPES)
    suspend fun search(
        @Header("Authorization") Authorization: String = "",
        @Query("page") page: Int = 1,
        @Query("query") query: String = "",
    ): Response<RecipeToken>
}