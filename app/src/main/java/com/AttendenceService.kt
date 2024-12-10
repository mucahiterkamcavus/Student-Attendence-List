package com


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AttendenceService {
    @POST("auth/local")
    fun login(@Body request : LoginData): Call<String>

    @GET("lessons")
    fun getTeacherLessons(@Query("filters[user][id]=") teacherId:String) : Call<String>


}