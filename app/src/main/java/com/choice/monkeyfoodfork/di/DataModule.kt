package com.choice.monkeyfoodfork.di

import android.app.Application
import androidx.room.Room
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
    fun providesRecipeDatabase(
        app: Application
    ) : AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        ).build()
    }
}