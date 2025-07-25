package com.example.userdirectory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()
    
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> = _users
    
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    
    init {
        loadUsers()
    }
    
    fun loadUsers() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val userList = repository.getUsers()
                _users.value = userList
            } catch (e: Exception) {
                _error.value = "Failed to load users: ${e.message}"
            } finally {
                _loading.value = false
            }
        }
    }
    
    fun addUser(user: User) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                repository.addUser(user)
                // Reload users to include the new user
                loadUsers()
            } catch (e: Exception) {
                _error.value = "Failed to add user: ${e.message}"
                _loading.value = false
            }
        }
    }
}
