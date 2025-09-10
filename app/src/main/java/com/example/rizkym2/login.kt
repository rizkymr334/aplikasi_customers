package com.example.rizkym2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class login {



    class login : AppCompatActivity() {

        private lateinit var dbHelper: DatabaseHelper

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_login)

            dbHelper = DatabaseHelper(this)

            val etUsername = findViewById<EditText>(R.id.etUsername)
            val etPassword = findViewById<EditText>(R.id.etPassword)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            btnLogin.setOnClickListener {
                val username = etUsername.text.toString()
                val password = etPassword.text.toString()

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val isValid = dbHelper.checkUser(username, password)
                if (isValid) {
                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, main.MainActivity::class.java)

                    intent.putExtra("username", username) // Kirim data username
                    startActivity(intent)
                    // Tutup LoginActivity agar tidak bisa balik pakai tombol back
                    finish()
                } else {
                    Toast.makeText(this, "Login gagal: user tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}