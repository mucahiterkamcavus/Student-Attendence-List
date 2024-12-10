package com.example.studentattendencelist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.AttendenceService
import com.Lessons
import com.RetrofitClient
import com.recylerview.TeacherLessonDataAdapter
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.nvLessonList)
        val token = intent.getStringExtra("jwt")
        val userid = intent.getStringExtra("userid").toString()
        val email = intent.getStringExtra("email")

        Toast.makeText(applicationContext,"hoşgeldiniz"+ email, Toast.LENGTH_SHORT).show()

        getTeacherLessons(userid)

    }
    fun getTeacherLessons ( id : String) {
        val service = RetrofitClient()
            .getRetrofit()
            .create(AttendenceService::class.java)
        service.getTeacherLessons(id).enqueue(object : Callback<String> {
            override fun onResponse(p0: Call<String>, p1: Response<String>) {

                val liste: ArrayList<Lessons> = java.util.ArrayList()
                val jsonObj = JSONObject(p1.body().toString())
                val jsonArray : JSONArray = jsonObj.getJSONArray("data")
                for (i in 0 until  jsonArray.length()) {
                    val id = jsonArray.getJSONObject(i).get("id").toString()
                    val name = jsonArray.getJSONObject(i).get("name").toString()
                    liste.add(Lessons(id,name))
                }
                val adapter = TeacherLessonDataAdapter(liste)
                recyclerView.layoutManager = LinearLayoutManager(applicationContext)
                recyclerView.adapter = adapter

            }

            override fun onFailure(p0: Call<String>, p1: Throwable) {
                Log.d("GELENDATA", "Hata Alındı"+p1.message.toString())
            }

        })

    }
}