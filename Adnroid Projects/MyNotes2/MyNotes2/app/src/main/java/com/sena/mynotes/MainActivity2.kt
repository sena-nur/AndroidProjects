package com.sena.mynotes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.prefs.PreferenceChangeEvent

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val editTextUserName = findViewById<EditText>(R.id.ed_userName)
        val editTextPassword = findViewById<EditText>(R.id.ed_password)
        val btnRegister = findViewById<Button>(R.id.button)

        btnRegister.setOnClickListener {
            val username = editTextUserName.text.toString()
            val password = editTextPassword.text.toString()

            val preference = getSharedPreferences("users", Context.MODE_PRIVATE)
            val editor = preference.edit()
            editor.putString(username, password).apply()


            Toast.makeText(this, "Tebrikler, Giriş Yaptınız",Toast.LENGTH_LONG ).show()
        }
    }
}