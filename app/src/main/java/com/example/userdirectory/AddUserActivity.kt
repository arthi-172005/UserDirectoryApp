package com.example.userdirectory

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class AddUserActivity : AppCompatActivity() {
    
    private lateinit var editTextName: TextInputEditText
    private lateinit var editTextEmail: TextInputEditText
    private lateinit var editTextPhone: TextInputEditText
    private lateinit var editTextAddress: TextInputEditText
    private lateinit var editTextWebsite: TextInputEditText
    private lateinit var editTextCompany: TextInputEditText
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    
    private val userViewModel: UserViewModel by viewModels()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        
        setupViews()
        setupClickListeners()
        setupActionBar()
        observeViewModel()
    }
    
    private fun setupViews() {
        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextAddress = findViewById(R.id.editTextAddress)
        editTextWebsite = findViewById(R.id.editTextWebsite)
        editTextCompany = findViewById(R.id.editTextCompany)
        buttonSave = findViewById(R.id.buttonSave)
        buttonCancel = findViewById(R.id.buttonCancel)
    }
    
    private fun setupClickListeners() {
        buttonSave.setOnClickListener {
            saveUser()
        }
        
        buttonCancel.setOnClickListener {
            finish()
        }
    }
    
    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.add_user)
        }
    }
    
    private fun observeViewModel() {
        userViewModel.loading.observe(this) { isLoading ->
            buttonSave.isEnabled = !isLoading
            if (isLoading) {
                buttonSave.text = "Saving..."
            } else {
                buttonSave.text = getString(R.string.save)
            }
        }
        
        userViewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }
        
        userViewModel.users.observe(this) { users ->
            // When users list is updated (after adding), finish this activity
            if (!userViewModel.loading.value!!) {
                Toast.makeText(this, getString(R.string.user_added_successfully), Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish()
            }
        }
    }
    
    private fun saveUser() {
        val name = editTextName.text.toString().trim()
        val email = editTextEmail.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()
        val address = editTextAddress.text.toString().trim()
        val website = editTextWebsite.text.toString().trim()
        val company = editTextCompany.text.toString().trim()
        
        // Validation
        if (name.isEmpty()) {
            editTextName.error = getString(R.string.required_field)
            editTextName.requestFocus()
            return
        }
        
        if (email.isEmpty()) {
            editTextEmail.error = getString(R.string.required_field)
            editTextEmail.requestFocus()
            return
        }
        
        if (phone.isEmpty()) {
            editTextPhone.error = getString(R.string.required_field)
            editTextPhone.requestFocus()
            return
        }
        
        // Create user object
        val addressObj = if (address.isNotEmpty()) {
            Address(
                street = address,
                suite = "",
                city = "",
                zipcode = ""
            )
        } else null
        
        val companyObj = if (company.isNotEmpty()) {
            Company(name = company)
        } else null
        
        val user = User(
            id = 0, // Will be assigned by repository
            name = name,
            email = email,
            phone = phone,
            address = addressObj,
            website = website.ifEmpty { null },
            company = companyObj
        )
        
        userViewModel.addUser(user)
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
