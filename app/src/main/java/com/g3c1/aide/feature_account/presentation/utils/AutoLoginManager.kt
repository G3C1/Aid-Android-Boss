package com.g3c1.aide.feature_account.presentation.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class AutoLoginManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "AccountDataStore")

    private val id = stringPreferencesKey("id")
    private val password = stringPreferencesKey("password")

    suspend fun getAccountData(type: Types.AccoutDataType): String {
        val tokenData = context.dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[
                        when (type) {
                            Types.AccoutDataType.ID -> id
                            Types.AccoutDataType.PASSWORD -> password
                        }
                ] ?: ""
            }
        return tokenData.first()
    }

    suspend fun setTokenData(data: String, type: Types.AccoutDataType) {
        context.dataStore.edit { preferences ->
            preferences[
                    when (type) {
                        Types.AccoutDataType.ID -> id
                        Types.AccoutDataType.PASSWORD -> password
                    }
            ] = data
        }
    }
}