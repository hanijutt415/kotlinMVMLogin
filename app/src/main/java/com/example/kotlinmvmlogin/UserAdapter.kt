package com.example.loginuserkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinmvmlogin.R

class UserAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    // ViewHolder to hold references to the views
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val firstName: TextView = itemView.findViewById(R.id.firstName)
        val lastName: TextView = itemView.findViewById(R.id.lastName)
        val email: TextView = itemView.findViewById(R.id.email)
        val password: TextView = itemView.findViewById(R.id.pass)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        // Inflate the item_user layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view)
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        // Bind the user data to the views
        val user = userList[position]
        holder.firstName.text = user.firstName
        holder.lastName.text = user.lastName
        holder.email.text = user.email
        holder.password.text = user.password
    }
    override fun getItemCount():
            Int = userList.size
}
