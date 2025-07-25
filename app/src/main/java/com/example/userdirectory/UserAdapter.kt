package com.example.userdirectory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(private val onUserClick: (User) -> Unit) : 
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_user, parent, false)
        return UserViewHolder(view, onUserClick)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class UserViewHolder(
        itemView: View,
        private val onUserClick: (User) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        private val emailTextView: TextView = itemView.findViewById(R.id.textViewEmail)
        private val phoneTextView: TextView = itemView.findViewById(R.id.textViewPhone)

        fun bind(user: User) {
            nameTextView.text = user.name
            emailTextView.text = user.email
            phoneTextView.text = user.phone
            
            itemView.setOnClickListener {
                onUserClick(user)
            }
        }
    }

    class UserDiffCallback : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }
}
