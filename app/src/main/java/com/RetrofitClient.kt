package com

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {

    private var INSTANCE: Retrofit? = null
    fun getRetrofit():Retrofit {
        val okHttpClient= OkHttpClient.Builder().build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return INSTANCE?:kotlin.run {
            synchronized(this) {
                Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl("https://api.dev.attandance.ogzatech.com/api/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
        }
    }

}