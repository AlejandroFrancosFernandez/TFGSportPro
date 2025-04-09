package com.example.tfgsportpro.features.f00_Auth.login.data

import android.content.Context
import android.content.SharedPreferences
import com.example.tfgsportpro.R

//Guardar el login para que no se tenga q volver a iniciar sesion
class UserPreferences(private val context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.prefs_file), Context.MODE_PRIVATE)

    fun saveEmail(email: String) {
        prefs.edit().putString("email", email).apply()
    }

    fun getEmail(): String? {
        return prefs.getString("email", null)
    }
}