package com.example.userdirectory

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
    
    @POST("users")
    suspend fun addUser(@Body user: User): Response<User>
}
