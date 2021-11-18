package com.example.aaplaunchtask

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class SharePref(context: Context) {
    private val DEFAULT_PREF_NAME: kotlin.String? = "ktools_pref"
    private val preferences: SharedPreferences =
        context.getSharedPreferences(DEFAULT_PREF_NAME, MODE_PRIVATE)


    private var editor: SharedPreferences.Editor? = preferences.edit()

    fun getUser(key: String): Boolean {
        return preferences.getBoolean(key, false);
    }

    fun putBoolean(
        key: String,
        value: Boolean
    ) {
        editor?.putBoolean(key, value)?.commit()

    }

    fun logout() {
        editor?.clear()
            ?.commit()
    }
}