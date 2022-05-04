package com.choice.monkeyfoodfork.di

import com.choice.core.modules.Network
import com.choice.irepository.IRecipeRepository
import com.choice.monkeyfoodfork.database.AppDatabase
import com.choice.remote.ApiFoodFork
import com.choice.repository.RecipeRepository
import com.choice.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesRecipeRepository(
        @Network.Food2Fork apiFoodFork: ApiFoodFork,
        db: AppDatabase
    ) : RecipeRepository{
        return IRecipeRepository(apiFoodFork, db.recipeDao)
    }

    @Provides
    @Singleton
    fun providesRecipeUseCases(repository: RecipeRepository) : RecipeUseCases {
        return RecipeUseCases(
            getRecipes = GetRecipesUseCases(repository),
            getRecipeById = RecipeGetByIdUseCases(repository),
            searchQuery = RecipeSearchQueryUseCases(repository),
            setFavorite = RecipeSetFavoriteUseCases(repository)
        )
    }
}