package com.aldana.jhersin.laboratoriocalificado3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aldana.jhersin.laboratoriocalificado3.databinding.ActivityEjercicio01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Ejercicio01Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding
    private lateinit var adapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        fetchDataFromApi()
    }

    private fun setupRecyclerView() {
        adapter = TeacherAdapter(this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun fetchDataFromApi() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiService::class.java)
        val call = service.getTeachers()

        call.enqueue(object : Callback<TeachersResponse> {
            override fun onResponse(call: Call<TeachersResponse>, response: Response<TeachersResponse>) {
                if (response.isSuccessful) {
                    val teachers = response.body()?.teachers ?: emptyList()
                    adapter.updateData(teachers)
                }
            }

            override fun onFailure(call: Call<TeachersResponse>, t: Throwable) {
                // Manejo de error
            }
        })
    }
}

