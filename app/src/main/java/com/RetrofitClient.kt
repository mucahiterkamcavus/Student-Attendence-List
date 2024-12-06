package com

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

class RetrofitClient {

    private var INSTANCE: Retrofit? = null
    fun getRetrofit():Retrofit {
        val okHttpClient= OkHttpClient.Builder().build()
        return INSTANCE?:kotlin.run {
            synchronized(this) {
                Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .baseUrl("https://api.dev.attandance.ogzatech.com/api/")
                    .build()
            }
        }
    }

}