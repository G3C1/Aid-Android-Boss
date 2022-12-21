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

class TokenManager(private val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "tokenDataStore")

    private val access = stringPreferencesKey("access")
    private val refresh = stringPreferencesKey("refresh")
    private val expired = stringPreferencesKey("expired")

    suspend fun getTokenData(type: Types.TokenType): String {
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
                            Types.TokenType.ACCESS -> access
                            Types.TokenType.REFRESH -> refresh
                            Types.TokenType.EXPIRED -> expired
                        }
                ] ?: ""
            }
        return tokenData.first()
    }

    suspend fun setTokenData(data: String, type: Types.TokenType) {
        context.dataStore.edit { preferences ->
            preferences[
                    when (type) {
                        Types.TokenType.ACCESS -> access
                        Types.TokenType.REFRESH -> refresh
                        Types.TokenType.EXPIRED -> expired
                    }
            ] = data
        }
    }
}