package com.cj.dysarthriachecker.frameworks.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.cj.dysarthriachecker.userManagement.models.AuthInfoModel
import kotlinx.coroutines.flow.map

const val DYSARTHRIA_CHECKER_DATASTORE = "DYSARTHRIA_CHECKER_DATASTORE"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DYSARTHRIA_CHECKER_DATASTORE)

class DataStoreUtil(val context : Context) {
    companion object{
        val AUTH_EMAIL = stringPreferencesKey("AUTH_EMAIL")
        val AUTH_PASSWORD = stringPreferencesKey("AUTH_PASSWORD")
    }

    suspend fun saveToDataStore(email : String, password : String){
        context.dataStore.edit{
            it[AUTH_EMAIL] = email
            it[AUTH_PASSWORD] = password
        }
    }

    fun getFromDataStore() = context.dataStore.data.map{
        AuthInfoModel(
            email = it[AUTH_EMAIL] ?: "",
            password = it[AUTH_PASSWORD] ?: ""
        )
    }

    suspend fun clearDataStore() = context.dataStore.edit{
        it.clear()
    }
}