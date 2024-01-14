package com.example.retrofit_vs_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_vs_recyclerview.adapter.CommentAdapter
import com.example.retrofit_vs_recyclerview.api.APIService
import com.example.retrofit_vs_recyclerview.models.Comment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    val BASE_URL2: String = "https://jsonplaceholder.typicode.com/"
    val TAG: String = "retrofit_intro"

    private lateinit var rcvComment: RecyclerView
    private lateinit var mCommentAdapter: CommentAdapter
    private lateinit var commentList: List<Comment>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rcvComment = findViewById(R.id.rcv_comment)
        mCommentAdapter = CommentAdapter(this)

        val linearLayoutManager = LinearLayoutManager(this)
        rcvComment.layoutManager = linearLayoutManager
//        mCommentAdapter.setData(getCommentList())
//        rcvComment.adapter = mCommentAdapter
        getComments()
    }

//    private fun getCommentList(): List<Comment> {
//        val commentList: MutableList<Comment> = ArrayList()
//        commentList.add(Comment(1, 1, "xung", "xung@gmail", "hello"))
//        commentList.add(Comment(2, 1, "xung", "xung@gmail", "hello"))
//        commentList.add(Comment(3, 1, "xung", "xung@gmail", "hello"))
//        return commentList
//    }

    private fun getComments() {
        val apiService: APIService = Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIService::class.java)

        //Đầu tiên, vào khai báo permission internet trong manifest
        //Sau đó:
        apiService.getComments().enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                if (response.isSuccessful) {
                    response.body()?.let { mCommentAdapter.setData(it) }
                    rcvComment.adapter = mCommentAdapter
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.i(TAG, "onFailure: ${t.message}")
            }
        })
    }
}