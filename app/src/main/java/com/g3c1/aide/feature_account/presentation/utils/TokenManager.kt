package com.g3c1.aide.feature_account.presentation.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class TokenManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "tokenDataStore")

    private val access = stringPreferencesKey("access")
    private val refresh = stringPreferencesKey("refresh")
    private val expired = stringPreferencesKey("expired")

    val accessToken: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[access] ?: ""
        }

    val refreshToken: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[refresh] ?: ""
        }

    val expiredAt: Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[expired] ?: ""
        }

    suspend fun setTokenData(data: String, type: String) {
        context.dataStore.edit { preferences ->
            when (type) {
                "access" -> preferences[access] = data
                "refresh" -> preferences[refresh] = data
                "expired" -> preferences[expired] = data
            }
        }
    }
}