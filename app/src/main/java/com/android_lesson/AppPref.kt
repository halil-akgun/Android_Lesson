package com.android_lesson

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var context: Context) {
    val Context.ds: DataStore<Preferences> by preferencesDataStore(name = "app_pref")

    companion object {
        val KEY_NAME = stringPreferencesKey("name")
        val KEY_AGE = intPreferencesKey("age")
        val KEY_HEIGHT = doublePreferencesKey("height")
        val KEY_IS_ADULT = booleanPreferencesKey("isAdult")
        val KEY_FRIENDS = stringSetPreferencesKey("friends")
    }

    // save
    suspend fun saveName(name: String) {
        context.ds.edit {
            it[KEY_NAME] = name
        }
    }

    suspend fun saveAge(age: Int) {
        context.ds.edit {
            it[KEY_AGE] = age
        }
    }

    suspend fun saveHeight(height: Double) {
        context.ds.edit {
            it[KEY_HEIGHT] = height
        }
    }

    suspend fun saveIsAdult(isAdult: Boolean) {
        context.ds.edit {
            it[KEY_IS_ADULT] = isAdult
        }
    }

    suspend fun saveFriends(friends: Set<String>) {
        context.ds.edit {
            it[KEY_FRIENDS] = friends
        }
    }

    // read
    suspend fun getName(): String {
        return context.ds.data.first()[KEY_NAME] ?: ""
    }

    suspend fun getAge(): Int {
        return context.ds.data.first()[KEY_AGE] ?: 0
    }

    suspend fun getHeight(): Double {
        return context.ds.data.first()[KEY_HEIGHT] ?: 0.0
    }

    suspend fun getIsAdult(): Boolean {
        return context.ds.data.first()[KEY_IS_ADULT] ?: false
    }

    suspend fun getFriends(): Set<String> {
        return context.ds.data.first()[KEY_FRIENDS] ?: emptySet()
    }

    // delete
    suspend fun deleteName() {
        context.ds.edit {
            it.remove(KEY_NAME)
        }
    }

    suspend fun deleteAge() {
        context.ds.edit {
            it.remove(KEY_AGE)
        }
    }

    suspend fun deleteHeight() {
        context.ds.edit {
            it.remove(KEY_HEIGHT)
        }
    }

    suspend fun deleteIsAdult() {
        context.ds.edit {
            it.remove(KEY_IS_ADULT)
        }
    }

    suspend fun deleteFriends() {
        context.ds.edit {
            it.remove(KEY_FRIENDS)
        }
    }
}