package com.example.kotlinmvmlogin 

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinmvmlogin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val activityViewModel: ActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val firstName = binding.editTextFirstName.text.toString()
            val lastName = binding.editTextLastName.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = User(firstName = firstName, lastName = lastName, email = email, password = password)

            activityViewModel.saveUser(user).observe(this) { result ->
                result.onSuccess {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@MainActivity,LoginActivity::class.java)
                    startActivity(intent)
                    finish()

                }.onFailure { exception ->
                    Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
