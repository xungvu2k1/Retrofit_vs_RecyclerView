package com.example.retrofit_vs_recyclerview.api

import com.example.retrofit_vs_recyclerview.models.Comment
import retrofit2.Call
import retrofit2.http.GET

interface APIService {
    @GET("comments")
    fun getComments() : Call<List<Comment>>
}