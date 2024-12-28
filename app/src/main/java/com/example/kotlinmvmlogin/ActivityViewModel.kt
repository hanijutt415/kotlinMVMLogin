package com.example.kotlinmvmlogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class ActivityViewModel : ViewModel() {

    private val repository = Repository()

    // Function to save a user and return LiveData
    fun saveUser(user: User): LiveData<Result<String>> {
        return repository.saveUser(user)
    }
    fun loginUser(loginEmail:String,loginPassword:String): LiveData<Result<String>>{
        return repository.loginUser(loginEmail,loginPassword)
    }
    fun getAllUser():LiveData<Result<List<User>>>{
        return repository.getAllUser()
    }
}
