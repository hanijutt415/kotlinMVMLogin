package com.example.kotlinmvmlogin

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinmvmlogin.R
import com.example.kotlinmvmlogin.databinding.ActivityUserBinding


class  ActivityUser : AppCompatActivity() {

    private val activityViewModel: ActivityViewModel by viewModels()
    private lateinit var binding: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_user)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.userRecylerView.layoutManager = LinearLayoutManager(this)
        activityViewModel.getAllUser().observe(this){result->
            result.onSuccess { userList->
                binding.userRecylerView.adapter = UserAdapter(userList)
            }
                .onFailure { exception ->
                    Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
                }
        }
    }
}