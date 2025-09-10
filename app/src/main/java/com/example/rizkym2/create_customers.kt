package com.example.rizkym2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class create_customers : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etDob: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAccount: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_customers)

        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDob)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAccount = findViewById(R.id.etAccount)
        btnSave = findViewById(R.id.btnSave)

        val dbHelper = DatabaseHelper(this)

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val dob = etDob.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val account = etAccount.text.toString()

            if (name.isBlank() || dob.isBlank() || phone.isBlank() || email.isBlank() || account.isBlank()) {
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = dbHelper.insertCustomer(name, dob, phone, email, account)
            if (success) {
                Toast.makeText(this, "Customer created successfully", Toast.LENGTH_SHORT).show()

                // Kalau mau kembali ke halaman utama, ganti MainActivity::class.java
                val intent = Intent(this, main::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Failed to create customer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
