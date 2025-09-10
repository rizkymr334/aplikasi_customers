package com.example.rizkym2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
class main {



    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
            val btnLogout = findViewById<Button>(R.id.btnLogout)
            val btnCustomer = findViewById<Button>(R.id.btnCustomer)
            // Ambil username dari intent 1
            val username = intent.getStringExtra("username") ?: "User"
            tvWelcome.text = "Selamat Datang, $username"
            btnCustomer.setOnClickListener {

                val intent = Intent(this, customers::class.java)
                startActivity(intent)

                // Tutup MainActivity agar tidak bisa kembali dengan tombol back
                finish()
            }
            btnLogout.setOnClickListener {
                Toast.makeText(this, "Berhasil logout", Toast.LENGTH_SHORT).show()

                // Kembali ke LoginActivity
                val intent = Intent(this, login::class.java)
                startActivity(intent)

                // Tutup MainActivity agar tidak bisa kembali dengan tombol back
                finish()
            }
        }
    }
}