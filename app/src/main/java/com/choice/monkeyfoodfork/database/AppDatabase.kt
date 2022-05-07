package com.choice.monkeyfoodfork.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.choice.local.dao.RecipeDao
import com.choice.local.entity.RecipeEntity
import com.choice.local.mapping.Converter
import com.choice.monkeyfoodfork.database.AppDatabase.Companion.DATABASE_VERSION
import javax.inject.Singleton

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val recipeDao : RecipeDao

    companion object {
        const val DATABASE_VERSION = 5
        const val DATABASE_NAME = "monkeyrecipes_db"
    }
}