package com.choice.irepository.module

import com.choice.irepository.IRecipeRepository
import com.choice.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRecipeRepository(recipeSearchRepository: IRecipeRepository): RecipeRepository
}