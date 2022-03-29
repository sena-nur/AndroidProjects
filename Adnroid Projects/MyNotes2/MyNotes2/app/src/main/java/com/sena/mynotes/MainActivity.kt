package com.sena.mynotes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editTextUsername = findViewById<EditText>(R.id.ed_userName)
        val editTextPassword= findViewById<EditText>(R.id.ed_password)
        val buttonLogin = findViewById<Button>(R.id.button)
        val textViewRegister = findViewById<TextView>(R.id.register_textView)

        buttonLogin.setOnClickListener {
            val username = editTextUsername.text.toString()
            val password = editTextPassword.text.toString()

            val preference = getSharedPreferences("users", Context.MODE_PRIVATE)
            val pass = preference.getString(username , "")

            if(pass == password) {

                Toast.makeText(this, "tebrikler, giriş yapıldı" , Toast.LENGTH_LONG).show()
                val intent = Intent(this, ListActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Şifre ya da kullanıcı adı hatalı" , Toast.LENGTH_LONG).show()
            }

        }
//Register sayfasına yönlendirdik
        textViewRegister.setOnClickListener {
            val Intent = Intent(this , MainActivity2::class.java)
            startActivity(intent)
        }
    }
}