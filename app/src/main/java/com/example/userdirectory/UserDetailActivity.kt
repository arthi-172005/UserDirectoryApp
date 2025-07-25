package com.example.userdirectory

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class UserDetailActivity : AppCompatActivity() {
    
    private lateinit var nameTextView: TextView
    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var websiteTextView: TextView
    private lateinit var companyTextView: TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        
        setupViews()
        displayUserDetails()
        setupActionBar()
    }
    
    private fun setupViews() {
        nameTextView = findViewById(R.id.textViewDetailName)
        emailTextView = findViewById(R.id.textViewDetailEmail)
        phoneTextView = findViewById(R.id.textViewDetailPhone)
        addressTextView = findViewById(R.id.textViewDetailAddress)
        websiteTextView = findViewById(R.id.textViewDetailWebsite)
        companyTextView = findViewById(R.id.textViewDetailCompany)
    }
    
    private fun displayUserDetails() {
        val user = intent.getParcelableExtra<User>("user")
        
        user?.let {
            nameTextView.text = it.name
            emailTextView.text = it.email
            phoneTextView.text = it.phone
            addressTextView.text = it.address?.toString() ?: "N/A"
            websiteTextView.text = it.website ?: "N/A"
            companyTextView.text = it.company?.name ?: "N/A"
        }
    }
    
    private fun setupActionBar() {
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.user_details)
        }
    }
    
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
