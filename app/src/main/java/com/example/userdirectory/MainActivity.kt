package com.example.userdirectory

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var fabAddUser: FloatingActionButton
    private lateinit var userAdapter: UserAdapter
    
    private val userViewModel: UserViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        setupViews()
        setupRecyclerView()
        observeViewModel()
        setupClickListeners()
    }
    
    private fun setupViews() {
        recyclerView = findViewById(R.id.recyclerViewUsers)
        fabAddUser = findViewById(R.id.fabAddUser)
    }
    
    private fun setupRecyclerView() {
        userAdapter = UserAdapter { user ->
            openUserDetail(user)
        }
        
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }
    
    private fun observeViewModel() {
        userViewModel.users.observe(this) { users ->
            userAdapter.submitList(users)
        }
        
        userViewModel.loading.observe(this) { isLoading ->
            // You can show/hide a progress bar here if needed
            fabAddUser.visibility = if (isLoading) View.GONE else View.VISIBLE
        }
        
        userViewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
    }
    
    private fun setupClickListeners() {
        fabAddUser.setOnClickListener {
            openAddUser()
        }
    }
    
    private fun openUserDetail(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra("user", user)
        }
        startActivity(intent)
    }
    
    private fun openAddUser() {
        val intent = Intent(this, AddUserActivity::class.java)
        startActivityForResult(intent, ADD_USER_REQUEST_CODE)
    }
    
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_USER_REQUEST_CODE && resultCode == RESULT_OK) {
            // Refresh the user list
            userViewModel.loadUsers()
        }
    }
    
    companion object {
        private const val ADD_USER_REQUEST_CODE = 1001
    }
}
