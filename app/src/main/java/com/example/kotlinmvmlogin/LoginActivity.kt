package com.example.loginuserkotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.loginuserkotlin.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val activityViewModel:ActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardViewSignUp.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }

        binding.regBtn.setOnClickListener{
            val loginEmail = binding.regEmail.text.toString()
            val loginPassword = binding.regPassword.text.toString()

            if (loginEmail.isEmpty() || loginPassword.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            activityViewModel.loginUser(loginEmail,loginPassword).observe(this){result->
                result.onSuccess {
                    Toast.makeText(this, it, Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@LoginActivity,ActivityUser::class.java)
                    startActivity(intent)
                    finish()
                }
                    .onFailure { exception ->
                        Toast.makeText(this, "Error: ${exception.message}", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}