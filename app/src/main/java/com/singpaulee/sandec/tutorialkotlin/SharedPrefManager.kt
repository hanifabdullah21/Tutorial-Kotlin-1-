package com.singpaulee.sandec.tutorialkotlin

import android.content.Context
import android.content.SharedPreferences

//TODO SET CLASS SHAREDPREFERENCE
class SharedPrefManager(context: Context) {
    val PREF_NAME = "TUTORIALKOTLIN"
    val PRIVATE_MODE = 0

    val USERNAME = "USERNAME"
    val PASSWORD = "PASSWORD"
    val isLOGIN = "LOGIN"

    var context: Context
    var preferences: SharedPreferences
    var editor: SharedPreferences.Editor

    init {
        this.context = context
        preferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE)
        editor = preferences.edit()
    }

    fun savePrefString(key:String, value:String){
        editor.putString(key,value)
        editor.commit()
    }

    fun savePrefBoolean(key:String, value: Boolean){
        editor.putBoolean(key, value)
        editor.commit()
    }

    fun getUsername():String{
        return preferences.getString(USERNAME,"")
    }

    fun getPassword():String{
        return preferences.getString(PASSWORD,"")
    }

    fun getIsLogin():Boolean{
        return preferences.getBoolean(isLOGIN,false)
    }
}