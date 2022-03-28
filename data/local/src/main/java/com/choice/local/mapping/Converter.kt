package com.choice.local.mapping

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun storedRecipesToString(data: List<String>): String {
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.toJson(data, type)
    }

    @TypeConverter
    fun storedStringToRecipes(data: String?) : List<String>{
        val gson = Gson()
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(data, type)
    }

}