package com.aldana.jhersin.laboratoriocalificado3

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("list/teacher-b")
    fun getTeachers(): Call<TeachersResponse>
}
