package com.choice.monkeyfoodfork.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.choice.local.dao.RecipeDao
import com.choice.local.entity.RecipeEntity
import com.choice.local.mapping.Converter
import javax.inject.Singleton

private const val DATABASE_VERSION = 3
private const val DATABASE_NAME = "monkeyrecipes_database.db"
@Database(
    entities = [
        RecipeEntity::class
    ],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {

    class Builder(private val application: Application) {
        private val builder: RoomDatabase.Builder<AppDatabase>
            get() = Room.databaseBuilder(
                application.applicationContext,
                AppDatabase::class.java,
                DATABASE_NAME
            )
                .fallbackToDestructiveMigration()

        fun build(): AppDatabase = builder.build()

    }

    abstract fun recipesResultsDao() : RecipeDao
}