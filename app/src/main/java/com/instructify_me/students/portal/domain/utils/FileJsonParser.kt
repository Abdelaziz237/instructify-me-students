package com.instructify_me.students.portal.domain.utils

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.instructify_me.students.portal.domain.models.Category
import java.io.IOException


fun readJsonFromAssets(context: Context, fileName: String): String? {
    return try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
    }
}

fun parseJson(jsonString: String): List<Category> {
    val gson = Gson()
    val listType = object : TypeToken<List<Category>>() {}.type
    return gson.fromJson(jsonString, listType)
}