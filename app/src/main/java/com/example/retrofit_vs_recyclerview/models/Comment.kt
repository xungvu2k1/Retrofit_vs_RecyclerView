package com.example.retrofit_vs_recyclerview.models

data class Comment(
    val postId : Int,
    val id : Int,
    val name : String,
    val email : String,
    val body : String
)
