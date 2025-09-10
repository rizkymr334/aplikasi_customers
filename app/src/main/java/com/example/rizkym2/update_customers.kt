package com.example.rizkym2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class update_customers : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etDob: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var etBankAccount: EditText
    private lateinit var btnSave: Button

    private lateinit var dbHelper: DatabaseHelper
    private var customerId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ✅ Sesuai nama layout
        setContentView(R.layout.activity_update_customers)

        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDob)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etBankAccount = findViewById(R.id.etBankAccount)
        btnSave = findViewById(R.id.btnSave)

        dbHelper = DatabaseHelper(this)

        customerId = intent.getIntExtra("id", -1)
        val name = intent.getStringExtra("name")
        val dob = intent.getStringExtra("dob")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val account = intent.getStringExtra("account")

        etName.setText(name)
        etDob.setText(dob)
        etPhone.setText(phone)
        etEmail.setText(email)
        etBankAccount.setText(account)

        btnSave.setOnClickListener {
            val newName = etName.text.toString()
            val newDob = etDob.text.toString()
            val newPhone = etPhone.text.toString()
            val newEmail = etEmail.text.toString()
            val newAccount = etBankAccount.text.toString()

            if (newName.isBlank() || newDob.isBlank() || newPhone.isBlank() || newEmail.isBlank() || newAccount.isBlank()) {
                Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val success = dbHelper.updateCustomer(customerId, newName, newDob, newPhone, newEmail, newAccount)
            if (success) {
                Toast.makeText(this, "Customer updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Failed to update customer", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
