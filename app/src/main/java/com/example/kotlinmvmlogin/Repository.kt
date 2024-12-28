package com.example.kotlinmvmlogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.google.firebase.firestore.FirebaseFirestore

class Repository {

    private val db = FirebaseFirestore.getInstance()


    fun saveUser(user: User): LiveData<Result<String>> {
        val resultLiveData = MutableLiveData<Result<String>>()

        db.collection("Students").add(user)
            .addOnSuccessListener {
                resultLiveData.postValue(Result.success("User saved successfully"))
            }
            .addOnFailureListener { exception ->
                resultLiveData.postValue(Result.failure(exception))
            }

        return resultLiveData
    }
    fun loginUser(loginEmail: String, loginPassword: String): LiveData<Result<String>> {
        val resultLiveData = MutableLiveData<Result<String>>()

        db.collection("Students")
            .whereEqualTo("email", loginEmail)
            .whereEqualTo("password", loginPassword)
            .get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    resultLiveData.postValue(Result.success("Login successful"))
                } else {
                    resultLiveData.postValue(Result.failure(Exception("Invalid email or password")))
                }
            }
            .addOnFailureListener { exception ->
                resultLiveData.postValue(Result.failure(exception))
            }

        return resultLiveData
    }

    fun getAllUser():LiveData<Result<List<User>>> {
        val resultLiveData = MutableLiveData<Result<List<User>>>()
        db.collection("Students").get().addOnSuccessListener { querySnapshot ->
            val userList = querySnapshot.documents.mapNotNull { it.toObject(User::class.java) }
            resultLiveData.postValue(Result.success(userList))
        }
            .addOnFailureListener { exception ->
                resultLiveData.postValue(Result.failure(exception))
            }
        return resultLiveData
    }
}
