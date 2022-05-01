package com.choice.local.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import com.choice.core.dao.BaseDao
import com.choice.local.entity.RecipeEntity

@Dao
interface RecipeDao : BaseDao<RecipeEntity> {

    @Query("SELECT * FROM Recipes")
    suspend fun getAll(): List<RecipeEntity>?

    @Query("SELECT * FROM Recipes WHERE :id = id")
    suspend fun getById(id: Int): RecipeEntity?

    @Query("UPDATE Recipes SET favorite = :favorite WHERE id = :id")
    suspend fun favorite(id: Int, favorite: Boolean): Int

    @Query(
        "SELECT * FROM recipes WHERE LOWER(title) LIKE '%' || LOWER(:query) || '%' OR LOWER(publisher) LIKE '%' || LOWER(:query) || '%'"
    )
    suspend fun searchRecipeList(query: String): List<RecipeEntity>

    @Query("DELETE from Recipes")
    suspend fun deleteAll()
}