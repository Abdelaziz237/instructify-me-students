package com.instructify_me.students.core.domain.objects

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first


private val Context.dataStore by preferencesDataStore(name = "user_credentials")
class LocalClient(context: Context) {
    private val dataStore = context.dataStore

    suspend fun setAuthToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_AUTH_TOKEN] = token
        }
    }

    suspend fun getAuthToken(): String {
        val preferences = dataStore.data.first()
        return preferences[KEY_AUTH_TOKEN] ?: "NULL"
    }

    suspend fun setRefreshToken(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_REFRESH_TOKEN] = token
        }
    }

    suspend fun getRefreshToken(): String {
        val preferences = dataStore.data.first()
        return preferences[KEY_REFRESH_TOKEN] ?: "NULL"
    }

    suspend fun setStudentId(token: String) {
        dataStore.edit { preferences ->
            preferences[KEY_STUDENT_ID] = token
        }
    }

    suspend fun getStudentId(): String {
        val preferences = dataStore.data.first()
        return preferences[KEY_STUDENT_ID] ?: "NULL"
    }

    companion object{
        private val KEY_AUTH_TOKEN = stringPreferencesKey("authToken")
        private val KEY_REFRESH_TOKEN = stringPreferencesKey("refreshToken")
        private val KEY_STUDENT_ID = stringPreferencesKey("studentId")
    }

}