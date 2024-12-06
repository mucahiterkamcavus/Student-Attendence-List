package com.example.studentattendencelist

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.AttendenceService
import com.LoginData
import com.RetrofitClient
import org.json.JSONObject
import org.json.JSONTokener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtMail = findViewById<EditText>(R.id.edtEmail)
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val service = RetrofitClient()
                .getRetrofit()
                .create(AttendenceService::class.java)
            val request = LoginData(edtMail.text.toString(),edtPassword.text.toString())
            service.login(request).enqueue(object : Callback<String> {
                override fun onFailure(p0: Call<String>, p1: Throwable) {

                    Toast.makeText(applicationContext,"Hata Alındı",Toast.LENGTH_SHORT).show()

                }

                override fun onResponse(p0: Call<String>, p1: Response<String>) {

                    Toast.makeText(applicationContext,"Başarılı",Toast.LENGTH_SHORT).show()
                    val resp = p1.body().toString()
                    val token = JSONObject(resp).get("jwt").toString()
                    val email = JSONObject(resp).getJSONObject("user").get("email").toString()
                    val userid = JSONObject(resp).getJSONObject("user").get("id").toString()


                    val intent = Intent(applicationContext,MainActivity::class.java)
                    intent.putExtra("jwt",token)
                    intent.putExtra("jwt", userid)
                    startActivity(intent)
                }
            })
        }

    }
}