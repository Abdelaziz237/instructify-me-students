package com.instructify_me.students.core.domain.objects

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


private val Context.dataStore by preferencesDataStore(name = "user_credentials")
class LocalClient(context: Context) {
    private val dataStore = context.dataStore

    suspend fun setAutToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH_TOKEN] = token
        }
    }

    suspend fun getAuthToken(): String {
        val preferences = dataStore.data.first()
        return preferences[KEY_AUTH_TOKEN] ?: "NULL"
    }

    companion object{
        private val KEY_AUTH_TOKEN = stringPreferencesKey("authToken")
    }

}