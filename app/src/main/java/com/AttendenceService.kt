package com


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AttendenceService {
    @POST("auth/local")
    fun login(@Body request : LoginData): Call<String>


}