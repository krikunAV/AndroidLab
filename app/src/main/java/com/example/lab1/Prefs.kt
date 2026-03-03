package com.example.lab1

import android.content.Context

private const val PREFS_NAME = "settings"
private const val KEY_LOGIN = "login"
private const val KEY_PASS = "pass"
private const val KEY_AUTO = "auto"

fun saveCredentials(context: Context, login: String, pass: String) {
    val sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    sp.edit().putString(KEY_LOGIN, login).putString(KEY_PASS, pass).apply()
}

fun setAutoLogin(context: Context, enabled: Boolean) {
    val sp = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    sp.edit().putBoolean(KEY_AUTO, enabled).apply()
}

fun readLogin(context: Context): String? =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(KEY_LOGIN, null)

fun readPass(context: Context): String? =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getString(KEY_PASS, null)

fun readAutoLogin(context: Context): Boolean =
    context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).getBoolean(KEY_AUTO, false)

fun hasCredentials(context: Context): Boolean =
    !readLogin(context).isNullOrBlank() && !readPass(context).isNullOrBlank()