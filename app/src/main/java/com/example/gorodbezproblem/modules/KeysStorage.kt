package com.example.gorodbezproblem.modules

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

fun getEncryptedPreferences(context: Context): SharedPreferences {
    val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()

    return EncryptedSharedPreferences.create(
        context,
        "auth_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}

fun saveAuthToken(context: Context, token: String) {
    val prefs = getEncryptedPreferences(context)
    prefs.edit().putString("auth_token", token).apply()
}

fun getAuthToken(context: Context): String? {
    val prefs = getEncryptedPreferences(context)
    return prefs.getString("auth_token", null)
}

fun deleteAuthToken(context: Context) {
    val prefs = getEncryptedPreferences(context)
    prefs.edit().remove("auth_token").apply()
}