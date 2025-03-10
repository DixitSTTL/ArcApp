package com.app.myinapp.data.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE

class SharedPrefManager(context: Context) {

    val sharedPreferences = context.getSharedPreferences("MYINAPPpref", MODE_PRIVATE)
    private val editor = sharedPreferences.edit()


    fun setBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key,false)
    }

    fun setString(key: String, value: String?) {
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    fun getStringDefault(key: String,defaultValue:String): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

}