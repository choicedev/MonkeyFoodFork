package com.choice.monkeyfoodfork.di

import android.app.Application
import com.choice.local.dao.RecipeDao
import com.choice.monkeyfoodfork.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application) = AppDatabase.Builder(application).build()

    @Provides
    @Singleton
    fun provideRecipesResultsDao(database: AppDatabase): RecipeDao = database.recipesResultsDao()
}